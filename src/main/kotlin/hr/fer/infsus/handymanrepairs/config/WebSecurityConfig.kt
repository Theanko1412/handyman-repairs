package hr.fer.infsus.handymanrepairs.config

import com.fasterxml.jackson.databind.ObjectMapper
import hr.fer.infsus.handymanrepairs.repository.CustomerRepository
import hr.fer.infsus.handymanrepairs.repository.HandymanRepository
import hr.fer.infsus.handymanrepairs.service.impl.CustomUserDetailsService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    @Autowired
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
    @Autowired
    private val customAuthenticationFailureHandler: CustomAuthenticationFailureHandler,
    @Autowired
    private val customUserDetailsService: CustomUserDetailsService,
    @Autowired
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:5173")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        configuration.exposedHeaders = listOf("Authorization")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.OPTIONS).permitAll()
                    .requestMatchers("/auth/register/**", "/auth/login/**", "/auth/logout/**").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin {
                it.loginProcessingUrl("/auth/login")
                    .successHandler { request, response, authentication ->
                        response.status = HttpServletResponse.SC_OK
                        response.contentType = "application/json"
                        response.writer.write(objectMapper.writeValueAsString(mapOf("message" to "Login successful")))
                    }
                    .failureHandler(customAuthenticationFailureHandler)
                    .permitAll()
            }
            .exceptionHandling {
                it.authenticationEntryPoint(customAuthenticationEntryPoint)
            }
            .logout {
                it.logoutUrl("/auth/logout")
                    .logoutSuccessHandler { request, response, authentication ->
                        response.status = HttpServletResponse.SC_OK
                        response.contentType = "application/json"
                        response.writer.write(objectMapper.writeValueAsString(mapOf("message" to "Logout successful")))
                    }.permitAll()
            }

        return http.build()
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(customUserDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }
}

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper,
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException,
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json"
        response.writer.write(
            objectMapper.writeValueAsString(ApiError(
                statusCode = HttpServletResponse.SC_UNAUTHORIZED,
                status = "Unauthorized",
                message = "Request is not authorized.",
                path = request.servletPath,
            ))
        )
    }
}

@Component
class CustomAuthenticationFailureHandler(
    private val objectMapper: ObjectMapper,
    private val customerRepository: CustomerRepository,
    private val handymanRepository: HandymanRepository,
) : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException,
    ) {
        val username = request.getParameter("username")
        val account = customerRepository.findCustomerByEmail(username)
            ?: handymanRepository.findHandymanByEmail(username)

        if(account != null && !account.isAccountNonLocked) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = "application/json"
            response.writer.write(
                objectMapper.writeValueAsString(
                    ApiError(
                    statusCode = HttpServletResponse.SC_UNAUTHORIZED,
                    status = "Unauthorized",
                    message = "Account is locked due to too many strikes.",
                    path = request.servletPath,
                )
                )
            )
            return
        }
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json"
        response.writer.write(
            objectMapper.writeValueAsString(ApiError(
                statusCode = HttpServletResponse.SC_UNAUTHORIZED,
                status = "Unauthorized",
                message = "Invalid username or password.",
                path = request.servletPath,
            )
        )
        )
    }
}

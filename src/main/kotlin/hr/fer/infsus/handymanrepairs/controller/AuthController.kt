package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.config.ApiError
import hr.fer.infsus.handymanrepairs.model.dto.CustomerDTO
import hr.fer.infsus.handymanrepairs.model.dto.HandymanDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.impl.CustomerService
import hr.fer.infsus.handymanrepairs.service.impl.HandymanService
import hr.fer.infsus.handymanrepairs.service.impl.HomeOrWorkshopService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@Controller
@RequestMapping("/auth")
class AuthController(
    @Autowired
    private val customerService: CustomerService,
    @Autowired
    private val handymanService: HandymanService,
    @Autowired
    private val homeOrWorkshopService: HomeOrWorkshopService,
    @Autowired
    private val passwordEncoder: PasswordEncoder,
) {
    @GetMapping("/check")
    fun checkSession(request: HttpServletRequest): ResponseEntity<Any> {
        val session = request.getSession(false)
        return if (session != null) {
            ResponseEntity.ok(mapOf("message" to "Session active"))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ApiError(
                    statusCode = HttpStatus.UNAUTHORIZED.value(),
                    status = HttpStatus.UNAUTHORIZED.reasonPhrase,
                    message = "Session not active",
                    path = request.requestURI,
                ),
            )
        }
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<Any> {
        val session = request.getSession(false)
        session?.invalidate()
        return ResponseEntity.ok(mapOf("message" to "Logged out successfully"))
    }

    @PostMapping("/register/customer")
    @ResponseBody
    fun registerCustomer(
        @RequestBody customerDTO: CustomerDTO,
    ): ResponseEntity<String> {
        val existingCustomer = customerService.getCustomerByEmail(customerDTO.email)
        val existingHandyman = handymanService.getHandymanByEmail(customerDTO.email)
        require(existingCustomer == null && existingHandyman == null) { "User with email ${customerDTO.email} already exists" }
        val customer =
            customerDTO.toDAO()
        customer.customerPassword = passwordEncoder.encode(customer.customerPassword)
        customerService.addCustomer(customer)
        return ResponseEntity.ok("Customer registered successfully")
    }

    @PostMapping("/register/handyman")
    @ResponseBody
    fun registerHandyman(
        @RequestBody handymanDTO: HandymanDTO,
    ): ResponseEntity<String> {
        val existingCustomer = customerService.getCustomerByEmail(handymanDTO.email)
        val existingHandyman = handymanService.getHandymanByEmail(handymanDTO.email)
        require(existingCustomer == null && existingHandyman == null) { "User with email ${handymanDTO.email} already exists" }
        val handyman =
            handymanDTO.toDAO()
        handyman.handymanPassword = passwordEncoder.encode(handyman.handymanPassword)
        handymanService.addHandyman(handyman)
        return ResponseEntity.ok("Handyman registered successfully")
    }

    @GetMapping("/user")
    fun getUser(
        @RequestParam email: String,
        request: HttpServletRequest,
    ): ResponseEntity<Any> {
        val customer = customerService.getCustomerByEmail(email)?.toDTO()
        val handyman = handymanService.getHandymanByEmail(email)?.toDTO()
        return if (customer != null) {
            ResponseEntity.ok(customer)
        } else if (handyman != null) {
            ResponseEntity.ok(handyman)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiError(
                    statusCode = HttpStatus.NOT_FOUND.value(),
                    status = HttpStatus.NOT_FOUND.reasonPhrase,
                    message = "User with email $email not found",
                    path = request.requestURI,
                ),
            )
        }
    }
}

@RestController
@RequestMapping("/")
class DummyController {
    @GetMapping
    fun getDummy(): String {
        return "Hello World"
    }
}

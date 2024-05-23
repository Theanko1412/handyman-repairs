package hr.fer.infsus.handymanrepairs.config

import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(
        e: EntityNotFoundException,
        request: HttpServletRequest,
    ): ResponseEntity<ApiError> {
        val apiError =
            ApiError(
                statusCode = 404,
                status = "Not Found",
                message = e.message ?: "Entity not found",
                path = request.requestURI,
            )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        e: IllegalArgumentException,
        request: HttpServletRequest,
    ): ResponseEntity<ApiError> {
        val apiError =
            ApiError(
                statusCode = 400,
                status = "Bad Request",
                message = e.message ?: "Bad Request",
                path = request.requestURI,
            )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(
        e: UsernameNotFoundException,
        request: HttpServletRequest,
    ): ResponseEntity<ApiError> {
        val apiError =
            ApiError(
                statusCode = 404,
                status = "Not Found",
                message = e.message ?: "User not found",
                path = request.requestURI,
            )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
        e: Exception,
        request: HttpServletRequest,
    ): ResponseEntity<ApiError> {
        val apiError =
            ApiError(
                statusCode = 500,
                status = "Internal Server Error",
                message = e.message ?: "Internal Server Error",
                path = request.requestURI,
            )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError)
    }
}

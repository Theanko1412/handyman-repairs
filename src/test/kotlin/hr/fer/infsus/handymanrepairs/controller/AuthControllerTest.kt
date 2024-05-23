package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dto.CustomerDTO
import hr.fer.infsus.handymanrepairs.model.dto.HandymanDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.service.impl.CustomerService
import hr.fer.infsus.handymanrepairs.service.impl.HandymanService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import jakarta.servlet.http.HttpServletRequest
import org.apache.catalina.session.StandardSession
import org.springframework.http.HttpStatus

class AuthControllerTest : FunSpec({

    val customerServiceMockk = mockk<CustomerService>()
    val handymanServiceMockk = mockk<HandymanService>()
    val controller = AuthController(customerServiceMockk, handymanServiceMockk, mockk())
    val passwordEncoderMock = mockk<org.springframework.security.crypto.password.PasswordEncoder>()
    val dummyController = DummyController()

    beforeTest {
        mockkStatic("org.springframework.http.ResponseEntity")
    }

    afterTest {
        unmockkStatic("org.springframework.http.ResponseEntity")
    }

    context("dummy") {
        test("dummy test") {
            dummyController.getDummy() shouldBe "Hello World"
        }
    }

    context("get") {
        test("/check should return 200 OK") {
            val request = mockk<HttpServletRequest>()

            every { request.getSession(false) } returns StandardSession(null)

            val response = controller.checkSession(request)

            response.statusCode shouldBe HttpStatus.OK
        }

        xtest("/check should return 401 UNAUTHORIZED") {
            val request = mockk<HttpServletRequest>()

            every { request.getSession(false) } returns null

            val response = controller.checkSession(request)

            response.statusCode shouldBe HttpStatus.UNAUTHORIZED
        }

        xtest("/user should return fail") {
            every { customerServiceMockk.getCustomerByEmail("1") } returns null
            every { handymanServiceMockk.getHandymanByEmail("1") } returns null

            val response = controller.getUser("1", mockk())

            response.statusCode shouldBe HttpStatus.NOT_FOUND
        }

        xtest("/user should return customer") {
            every { customerServiceMockk.getCustomerByEmail("1") } returns
                Customer(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.CUSTOMER,
                    1,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                )

            val response = controller.getUser("1", mockk())

            response.statusCode shouldBe HttpStatus.OK
        }

        xtest("/user should return handyman") {
            every { handymanServiceMockk.getHandymanByEmail("1") } returns
                Handyman(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.HANDYMAN,
                    2.0,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                    null,
                )

            val response = controller.getUser("1", mockk())

            response.statusCode shouldBe HttpStatus.OK
        }
    }

    context("post") {
        xtest("/registerCustomer") {
            val customerDTO =
                CustomerDTO(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.CUSTOMER.toString(),
                    1,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                )

            every { passwordEncoderMock.encode("password") } returns "password"
            every { customerServiceMockk.getCustomerByEmail("email") } returns null
            every { handymanServiceMockk.getHandymanByEmail("email") } returns null
            every { customerServiceMockk.addCustomer(any()) } returns customerDTO.toDAO()

            val response = controller.registerCustomer(customerDTO)

            response.statusCode shouldBe HttpStatus.OK
        }

        xtest("/registerHandyman") {
            val handymanDTO =
                HandymanDTO(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    CustomerType.HANDYMAN.toString(),
                    "password",
                    2.0,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                    null,
                )

            every { customerServiceMockk.getCustomerByEmail("email") } returns null
            every { handymanServiceMockk.getHandymanByEmail("email") } returns null
            every { handymanServiceMockk.addHandyman(any()) } returns handymanDTO.toDAO()
            every { passwordEncoderMock.encode("password") } returns "password"

            val response = controller.registerHandyman(handymanDTO)

            response.statusCode shouldBe HttpStatus.OK
        }

        xtest("/register/customer throws exception user already exists") {
            val customerDTO =
                CustomerDTO(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.CUSTOMER.toString(),
                    1,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                )

            every { customerServiceMockk.getCustomerByEmail("email") } returns
                Customer(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.CUSTOMER,
                    1,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                )

            val exception =
                shouldThrow<IllegalArgumentException> {
                    controller.registerCustomer(customerDTO)
                }

            exception.message shouldBe "User with email email already exists"
        }

        xtest("/register/handyman throws exception user already exists") {
            val handymanDTO =
                HandymanDTO(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    CustomerType.HANDYMAN.toString(),
                    "password",
                    2.0,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                    null,
                )

            every { handymanServiceMockk.getHandymanByEmail("email") } returns
                Handyman(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.HANDYMAN,
                    2.0,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                    null,
                )

            val exception =
                shouldThrow<IllegalArgumentException> {
                    controller.registerHandyman(
                        handymanDTO,
                    )
                }

            exception.message shouldBe "User with email email already exists"
        }
    }
})

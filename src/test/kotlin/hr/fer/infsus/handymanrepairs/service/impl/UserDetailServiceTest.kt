package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.repository.CustomerRepository
import hr.fer.infsus.handymanrepairs.repository.HandymanRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailServiceTest : FunSpec({

    val customerRepository = mockk<CustomerRepository>()
    val handymanRepository = mockk<HandymanRepository>()

    val userDetailService = UserDetailService(customerRepository, handymanRepository)

    test("loadUserByUsername found customer") {
        val customer =
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

        every { customerRepository.findCustomerByEmail("email") } returns customer

        val userDetails = userDetailService.loadUserByUsername("email")

        userDetails shouldBe customer
    }

    test("loadUserByUsername found handyman") {
        val handyman =
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

        every { customerRepository.findCustomerByEmail("email") } returns null
        every { handymanRepository.findHandymanByEmail("email") } returns handyman

        val userDetails = userDetailService.loadUserByUsername("email")

        userDetails shouldBe handyman
    }

    test("should throw exception if not found customer or handyman") {
        every { customerRepository.findCustomerByEmail("email") } returns null
        every { handymanRepository.findHandymanByEmail("email") } returns null

        val exception =
            shouldThrow<UsernameNotFoundException> {
                userDetailService.loadUserByUsername("email")
            }

        exception.message shouldBe "User not found with email: email"
    }
})

package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dto.CustomerDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.service.impl.CustomerService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityNotFoundException

class CustomerControllerTest : FunSpec({

    val serviceMock = mockk<CustomerService>()
    val controller = CustomerController(serviceMock)

    val expectedCustomers =
        listOf(
            CustomerDTO(
                "1",
                "John",
                "Doe",
                "email",
                "***",
                CustomerType.CUSTOMER.toString(),
                1,
                false,
                null,
                emptyList(),
                emptyList(),
            ),
            CustomerDTO(
                "2",
                "Jane",
                "Doe",
                "email",
                "***",
                CustomerType.CUSTOMER.toString(),
                1,
                false,
                null,
                emptyList(),
                emptyList(),
            ),
        )

    context("get") {
        test("get all") {
            every { serviceMock.getAllCustomers() } returns expectedCustomers.map { it.toDAO() }

            val result = controller.getAllCustomers()

            result shouldBe expectedCustomers
        }

        test("get by id") {
            every { serviceMock.getCustomerById("1") } returns expectedCustomers[0].toDAO()

            val result = controller.getCustomerById("1")

            result shouldBe expectedCustomers[0]
        }

        test("get by id with email") {
            every { serviceMock.getCustomerByEmail("email@email") } returns expectedCustomers[0].toDAO()

            val result = controller.getCustomerById("email@email")

            result shouldBe expectedCustomers[0]
        }

        test("get by id should thow exception") {
            every { serviceMock.getCustomerById("1") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getCustomerById("1")
                }

            exception.message shouldBe "Customer with id 1 does not exist"
        }

        test("get by id with email should thow exception") {
            every { serviceMock.getCustomerByEmail("email@email") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getCustomerById("email@email")
                }

            exception.message shouldBe "Customer with email email@email does not exist"
        }
    }
})

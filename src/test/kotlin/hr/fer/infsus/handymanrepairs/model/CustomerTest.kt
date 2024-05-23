package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dto.CustomerDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CustomerTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
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

            val customerDTO = customer.toDTO()

            customerDTO.id shouldBe "1"
            customerDTO.firstName shouldBe "John"
            customerDTO.lastName shouldBe "Doe"
            customerDTO.email shouldBe "email"
            customerDTO.password shouldBe "***"
            customerDTO.type shouldBe CustomerType.CUSTOMER.toString()
            customerDTO.strikes shouldBe 1
            customerDTO.isSuspended shouldBe false
            customerDTO.homeOrWorkshopId shouldBe null
            customerDTO.notificationIds shouldBe emptyList()
            customerDTO.notificationIds shouldBe emptyList()
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
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

            val customer = customerDTO.toDAO()

            customer.id shouldBe "1"
            customer.firstName shouldBe "John"
            customer.lastName shouldBe "Doe"
            customer.email shouldBe "email"
            customer.password shouldBe "password"
            customer.type shouldBe CustomerType.CUSTOMER
            customer.strikes shouldBe 1
            customer.isSuspended shouldBe false
            customer.homeOrWorkshop shouldBe null
            customer.notifications shouldBe emptyList()
            customer.reservations shouldBe emptyList()
        }
    }
})

package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Notification
import hr.fer.infsus.handymanrepairs.model.dto.NotificationDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.OffsetDateTime

class NotificationTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val dao =
                Notification(
                    "1",
                    "message",
                    OffsetDateTime.parse("2021-06-22T10:15:30+01:00"),
                    "sender",
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
                    ),
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
                    ),
                )

            val dto = dao.toDTO()

            dto.id shouldBe "1"
            dto.message shouldBe "message"
            dto.date shouldBe OffsetDateTime.parse("2021-06-22T10:15:30+01:00")
            dto.sender shouldBe "sender"
            dto.customerId shouldBe "1"
            dto.handymanId shouldBe "1"
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val dto =
                NotificationDTO(
                    "1",
                    "message",
                    OffsetDateTime.parse("2021-06-22T10:15:30+01:00"),
                    "sender",
                    "1",
                    "1",
                )

            val dao =
                dto.toDAO(
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
                    ),
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
                    ),
                )

            dao.id shouldBe "1"
            dao.message shouldBe "message"
            dao.date shouldBe OffsetDateTime.parse("2021-06-22T10:15:30+01:00")
            dao.sender shouldBe "sender"
            dao.customer.id shouldBe "1"
            dao.handyman.id shouldBe "1"
        }
    }
})

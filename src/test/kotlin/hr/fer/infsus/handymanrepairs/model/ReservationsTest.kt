package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.model.dao.Service
import hr.fer.infsus.handymanrepairs.model.dao.Status
import hr.fer.infsus.handymanrepairs.model.dto.ReservationDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.OffsetDateTime

class ReservationsTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val dao = Reservation(
                "1",
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
                Status.PENDING,
                Schedule(
                    "1",
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
                    emptyList(),
                ),
                Service(
                    "1",
                    "Service",
                    "description",
                    10,
                    10,
                    Category(
                        "1",
                        "Category",
                        "description",
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
                    mutableListOf(),
                ),
                OffsetDateTime.parse("2021-01-01T00:00:00Z"),
            )

            val dto = dao.toDTO()

            dto.id shouldBe "1"
            dto.status shouldBe Status.PENDING
            dto.customerId shouldBe "1"
            dto.scheduleId shouldBe "1"
            dto.serviceId shouldBe "1"
            dto.dateTime shouldBe "2021-01-01T00:00Z"
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val dto = ReservationDTO(
                "1",
                Status.PENDING,
                "1",
                "1",
                "1",
                "2021-01-01T00:00:00Z",
            )

            val dao = dto.toDAO(
                Schedule(
                    "1",
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
                    emptyList(),
                ),
                Service(
                    "1",
                    "Service",
                    "description",
                    10,
                    10,
                    Category(
                        "1",
                        "Category",
                        "description",
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
                    mutableListOf(),
                ),
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
            )

            dao.id shouldBe "1"
            dao.status shouldBe Status.PENDING
            dao.schedule.id shouldBe "1"
            dao.service.id shouldBe "1"
            dao.dateTime shouldBe OffsetDateTime.parse("2021-01-01T00:00:00Z")
            dao.customer.id shouldBe "1"
        }
    }
})
package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.model.dao.Service
import hr.fer.infsus.handymanrepairs.model.dao.Status
import hr.fer.infsus.handymanrepairs.model.dto.ReservationDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.IReservationService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import jakarta.persistence.EntityNotFoundException
import java.time.OffsetDateTime

class ReservationControllerTest : FunSpec({

    val serviceMock = mockk<IReservationService>()
    val controller = ReservationController(serviceMock)

    beforeTest {
        mockkStatic(Status::class)
    }

    afterTest {
        unmockkStatic(Status::class)
    }

    val expectedReservations =
        listOf(
            Reservation(
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
            ),
            Reservation(
                "2",
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
            ),
        )

    val givenDto =
        ReservationDTO(
            "1",
            Status.PENDING,
            "1",
            "1",
            "1",
            "2021-01-01T00:00:00Z",
        )

    context("get") {
        test("get all") {
            every { serviceMock.getAllReservations() } returns expectedReservations

            val result = controller.getReservations()

            result shouldBe expectedReservations.map { it.toDTO() }
        }

        test("get all with 2 filters applied throws exception") {
            val exception =
                shouldThrow<IllegalArgumentException> {
                    controller.getReservations("1", "1")
                }

            exception.message shouldBe "Only one filter can be used"
        }

        xtest("get all with non existant status should throw exception") {
            every { Status.entries.toTypedArray() } returns arrayOf(Status.ACCEPTED, Status.PENDING, Status.REJECTED)

            val exception =
                shouldThrow<IllegalArgumentException> {
                    controller.getReservations("1", status = Status.COMPLETED)
                }

            exception.message shouldBe "Invalid status, must be one of [ACCEPTED, PENDING, REJECTED]"
        }

        test("get when status not null by scheduleIdAndStatus") {
            every { serviceMock.getReservationsByScheduleIdAndStatus("1", Status.PENDING) } returns expectedReservations

            val result = controller.getReservations("1", status = Status.PENDING)

            result shouldBe expectedReservations.map { it.toDTO() }
        }

        test("get when status not null by customerIdAndStatus") {
            every { serviceMock.getReservationsByCustomerIdAndStatus("1", Status.PENDING) } returns expectedReservations

            val result = controller.getReservations(customerId = "1", status = Status.PENDING)

            result shouldBe expectedReservations.map { it.toDTO() }
        }

        test("get when status null by scheduleId") {
            every { serviceMock.getReservationsByScheduleId("1") } returns expectedReservations

            val result = controller.getReservations("1", null)

            result shouldBe expectedReservations.map { it.toDTO() }
        }

        test("get when status null by customerId") {
            every { serviceMock.getReservationsByCustomerId("1") } returns expectedReservations

            val result = controller.getReservations(null, "1")

            result shouldBe expectedReservations.map { it.toDTO() }
        }

        test("get by id") {
            every { serviceMock.getReservationById("1") } returns expectedReservations[0]

            val result = controller.getReservation("1")

            result shouldBe expectedReservations[0].toDTO()
        }

        test("get by id throws exception when returned null") {
            every { serviceMock.getReservationById("1") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getReservation("1")
                }

            exception.message shouldBe "Reservation with id 1 not found"
        }
    }

    context("add") {
        test("add reservation") {
            every { serviceMock.buildReservation(givenDto) } returns expectedReservations[0]
            every { serviceMock.addReservation(expectedReservations[0]) } returns expectedReservations[0]

            val result = controller.addReservation(givenDto)

            result shouldBe expectedReservations[0].toDTO()
        }
    }

    context("update") {
        test("update reservation") {
            every { serviceMock.updateReservationStatusById("1", Status.PENDING) } returns expectedReservations[0]

            val result = controller.updateReservation("1", Status.PENDING)

            result shouldBe expectedReservations[0].toDTO()
        }

        xtest("update reservation sohuld throw exception if not in enum") {
            every { Status.entries.toTypedArray() } returns arrayOf(Status.ACCEPTED, Status.PENDING, Status.REJECTED)

            val exception =
                shouldThrow<IllegalArgumentException> {
                    controller.updateReservation("1", Status.COMPLETED)
                }

            exception.message shouldBe "Invalid status, must be one of [ACCEPTED, PENDING, REJECTED]"
        }
    }
})

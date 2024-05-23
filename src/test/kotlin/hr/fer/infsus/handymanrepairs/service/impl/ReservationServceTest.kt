package hr.fer.infsus.handymanrepairs.service.impl

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
import hr.fer.infsus.handymanrepairs.repository.ReservationRepository
import hr.fer.infsus.handymanrepairs.service.ICustomerService
import hr.fer.infsus.handymanrepairs.service.IHandymanService
import hr.fer.infsus.handymanrepairs.service.IScheduleService
import hr.fer.infsus.handymanrepairs.service.IServiceService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import java.time.OffsetDateTime

class ReservationServceTest : FunSpec({

    val repositoryMock = mockk<ReservationRepository>()
    val scheduleServiceMock = mockk<IScheduleService>()
    val serviceServiceMock = mockk<IServiceService>()
    val customerServiceMock = mockk<ICustomerService>()
    val handymanServiceMock = mockk<IHandymanService>()

    val service =
        ReservationService(
            repositoryMock,
            scheduleServiceMock,
            serviceServiceMock,
            customerServiceMock,
            handymanServiceMock,
        )

    val dummyCustomer1 =
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

    val dummyHandyman1 =
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

    val dummyService1 =
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
            dummyHandyman1,
            mutableListOf(),
        )

    val dummySchedule1 =
        Schedule(
            "1",
            dummyHandyman1,
            emptyList(),
        )

    context("getReservations") {
        test("should return all reservations") {
            val expectedReservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                    Reservation(
                        "2",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "2",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                )

            every { repositoryMock.findAll() } returns expectedReservations

            val reservations = service.getAllReservations()

            reservations shouldBe expectedReservations
        }

        test("should return reservation by id") {
            val expectedReservation =
                Reservation(
                    "1",
                    dummyCustomer1,
                    Status.PENDING,
                    Schedule(
                        "1",
                        dummyHandyman1,
                        emptyList(),
                    ),
                    dummyService1,
                    OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                )

            every { repositoryMock.findReservationById("1") } returns expectedReservation

            val reservation = service.getReservationById("1")

            reservation shouldBe expectedReservation
        }

        test("should return reservations by schedule id") {
            val expectedReservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                    Reservation(
                        "2",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                )

            every { repositoryMock.findReservationsByScheduleId("1") } returns expectedReservations

            val reservations = service.getReservationsByScheduleId("1")

            reservations shouldBe expectedReservations
        }

        test("should return reservations by customer id") {
            val expectedReservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                    Reservation(
                        "2",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                )

            every { repositoryMock.findReservationsByCustomerId("1") } returns expectedReservations

            val reservations = service.getReservationsByCustomerId("1")

            reservations shouldBe expectedReservations
        }

        test("should return reservations by status") {
            val expectedReservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                    Reservation(
                        "2",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                )

            every { repositoryMock.findReservationsByStatus(Status.PENDING) } returns expectedReservations

            val reservations = service.getAllReservationsByStatus(Status.PENDING)

            reservations shouldBe expectedReservations
        }

        test("should return reservations by schedule id and status") {
            val expectedReservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                    Reservation(
                        "2",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                )

            every { repositoryMock.findReservationsByScheduleIdAndStatus("1", Status.PENDING) } returns expectedReservations

            val reservations = service.getReservationsByScheduleIdAndStatus("1", Status.PENDING)

            reservations shouldBe expectedReservations
        }

        test("should return reservations by customer id and status") {
            val expectedReservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                    Reservation(
                        "2",
                        dummyCustomer1,
                        Status.PENDING,
                        Schedule(
                            "1",
                            dummyHandyman1,
                            emptyList(),
                        ),
                        dummyService1,
                        OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                    ),
                )

            every { repositoryMock.findReservationsByCustomerIdAndStatus("1", Status.PENDING) } returns expectedReservations

            val reservations = service.getReservationsByCustomerIdAndStatus("1", Status.PENDING)

            reservations shouldBe expectedReservations
        }
    }

    context("addReservation") {
        test("should add reservation") {
            val reservation =
                Reservation(
                    "1",
                    dummyCustomer1,
                    Status.PENDING,
                    Schedule(
                        "1",
                        dummyHandyman1,
                        emptyList(),
                    ),
                    dummyService1,
                    OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                )

            val spyService = spyk(service)

            every { repositoryMock.save(reservation) } returns reservation
            every { spyService.validateReservation(reservation.toDTO()) } returns true

            val addedReservation = spyService.addReservation(reservation)

            addedReservation shouldBe reservation
        }

        test("should fail on validation") {
            val reservation =
                Reservation(
                    "1",
                    dummyCustomer1,
                    Status.PENDING,
                    Schedule(
                        "1",
                        dummyHandyman1,
                        emptyList(),
                    ),
                    dummyService1,
                    OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                )

            val spyService = spyk(service)

            every { spyService.validateReservation(reservation.toDTO()) } returns false

            shouldThrow<IllegalArgumentException> {
                spyService.addReservation(reservation)
            }
        }
    }

    context("modifyReservation") {
        test("should update reservation status by id") {
            val reservation =
                Reservation(
                    "1",
                    dummyCustomer1,
                    Status.PENDING,
                    Schedule(
                        "1",
                        dummyHandyman1,
                        emptyList(),
                    ),
                    dummyService1,
                    OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                )

            every { repositoryMock.findReservationById("1") } returns reservation
            every { repositoryMock.save(reservation) } returns reservation

            val updatedReservation = service.updateReservationStatusById("1", Status.ACCEPTED)

            updatedReservation.status shouldBe Status.ACCEPTED
        }

        test("should delete reservation by id") {

            every { repositoryMock.deleteReservationById("1") } returns Unit

            service.deleteReservationById("1") shouldBe Unit
        }

        test("should build reservation from dto") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2021-01-01T00:00:00Z",
                )

            val expectedReservation =
                Reservation(
                    "1",
                    dummyCustomer1,
                    Status.PENDING,
                    Schedule(
                        "1",
                        dummyHandyman1,
                        emptyList(),
                    ),
                    dummyService1,
                    OffsetDateTime.parse("2021-01-01T00:00:00Z"),
                )

            every { customerServiceMock.getCustomerById("1") } returns dummyCustomer1
            every { scheduleServiceMock.getScheduleById("1") } returns
                Schedule(
                    "1",
                    dummyHandyman1,
                    emptyList(),
                )
            every { serviceServiceMock.getServiceById("1") } returns dummyService1

            val spyService = spyk(service)

            val builtReservation = spyService.buildReservation(reservationDTO)

            builtReservation shouldBe expectedReservation.copy(id = null)
        }
    }

    context("validate reservation") {
        test("should return true on valid reservation") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2222-01-01T00:00:00Z",
                )

            every { customerServiceMock.getCustomerById("1") } returns dummyCustomer1
            every { scheduleServiceMock.getScheduleById("1") } returns dummySchedule1
            every { serviceServiceMock.getServiceById("1") } returns dummyService1

            val isValid = service.validateReservation(reservationDTO)

            isValid shouldBe true
        }

        test("shuold throw exception if customerId is empty") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "",
                    "1",
                    "1",
                    "2222-01-01T00:00:00Z",
                )

            val exception =
                shouldThrow<IllegalArgumentException> {
                    service.validateReservation(reservationDTO)
                }

            exception.message shouldBe "Customer ID must not be empty"
        }

        test("shuold throw exception if scheduleId is empty") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "",
                    "1",
                    "2222-01-01T00:00:00Z",
                )

            val exception =
                shouldThrow<IllegalArgumentException> {
                    service.validateReservation(reservationDTO)
                }

            exception.message shouldBe "Schedule ID must not be empty"
        }

        test("shuold throw exception if serviceId is empty") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "",
                    "2222-01-01T00:00:00Z",
                )

            val exception =
                shouldThrow<IllegalArgumentException> {
                    service.validateReservation(reservationDTO)
                }

            exception.message shouldBe "Service ID must not be empty"
        }

        test("should throw exception if date is in the past") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2020-01-01T00:00:00Z",
                )

            val exception =
                shouldThrow<IllegalArgumentException> {
                    service.validateReservation(reservationDTO)
                }

            exception.message shouldBe "Reservation date must be in the future"
        }

        test("should throw exception if customer does not exist") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2222-01-01T00:00:00Z",
                )

            every { customerServiceMock.getCustomerById("1") } returns null

            val exception =
                shouldThrow<IllegalArgumentException> {
                    service.validateReservation(reservationDTO)
                }

            exception.message shouldBe "Customer with ID 1 not found"
        }

        test("should throw exception if schedule does not exist") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2222-01-01T00:00:00Z",
                )

            every { customerServiceMock.getCustomerById("1") } returns dummyCustomer1
            every { scheduleServiceMock.getScheduleById("1") } returns null

            val exception =
                shouldThrow<IllegalArgumentException> {
                    service.validateReservation(reservationDTO)
                }

            exception.message shouldBe "Schedule with ID 1 not found"
        }

        test("should throw exception if service does not exist") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2222-01-01T00:00:00Z",
                )

            every { customerServiceMock.getCustomerById("1") } returns dummyCustomer1
            every { scheduleServiceMock.getScheduleById("1") } returns dummySchedule1
            every { serviceServiceMock.getServiceById("1") } returns null

            val exception =
                shouldThrow<IllegalArgumentException> {
                    service.validateReservation(reservationDTO)
                }

            exception.message shouldBe "Service with ID 1 not found"
        }

        test("should pass even if reservations on schedule is null") {
            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2222-01-01T00:00:00Z",
                )

            every { customerServiceMock.getCustomerById("1") } returns dummyCustomer1
            every { scheduleServiceMock.getScheduleById("1") } returns dummySchedule1
            every { serviceServiceMock.getServiceById("1") } returns dummyService1

            every { scheduleServiceMock.getScheduleById("1")?.reservations } returns null

            val isValid = service.validateReservation(reservationDTO)

            isValid shouldBe true
        }

        test("should fail if new reservation starts before prevoius ends") {
            val reservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        dummySchedule1,
                        dummyService1,
                        OffsetDateTime.parse("2222-01-01T00:00:00Z"),
                    ),
                )

            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2222-01-01T00:00:00Z",
                )

            every { customerServiceMock.getCustomerById("1") } returns dummyCustomer1
            every { scheduleServiceMock.getScheduleById("1") } returns dummySchedule1
            every { serviceServiceMock.getServiceById("1") } returns dummyService1
            every { scheduleServiceMock.getScheduleById("1")?.reservations } returns reservations

            val exception =
                shouldThrow<IllegalArgumentException> {
                    service.validateReservation(reservationDTO)
                }

            exception.message shouldStartWith "New reservation overlaps with an existing reservation"
        }

        test("should pass if new reservation starts after prevoius ends") {
            val reservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        dummySchedule1,
                        dummyService1,
                        OffsetDateTime.parse("2222-01-01T00:00:00Z"),
                    ),
                )

            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2222-01-01T20:00:00Z",
                )

            every { customerServiceMock.getCustomerById("1") } returns dummyCustomer1
            every { scheduleServiceMock.getScheduleById("1") } returns dummySchedule1
            every { serviceServiceMock.getServiceById("1") } returns dummyService1
            every { scheduleServiceMock.getScheduleById("1")?.reservations } returns reservations

            val isValid = service.validateReservation(reservationDTO)

            isValid shouldBe true
        }

        test("should fail if new reservation ends after prevoius starts") {
            val reservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        dummySchedule1,
                        dummyService1,
                        OffsetDateTime.parse("2222-01-01T00:00:00Z"),
                    ),
                )

            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2222-01-01T00:00:00Z",
                )

            every { customerServiceMock.getCustomerById("1") } returns dummyCustomer1
            every { scheduleServiceMock.getScheduleById("1") } returns dummySchedule1
            every { serviceServiceMock.getServiceById("1") } returns dummyService1
            every { scheduleServiceMock.getScheduleById("1")?.reservations } returns reservations

            val exception =
                shouldThrow<IllegalArgumentException> {
                    service.validateReservation(reservationDTO)
                }

            exception.message shouldStartWith "New reservation overlaps with an existing reservation"
        }

        test("should pass if new reservation ends before prevoius starts") {
            val reservations =
                listOf(
                    Reservation(
                        "1",
                        dummyCustomer1,
                        Status.PENDING,
                        dummySchedule1,
                        dummyService1,
                        OffsetDateTime.parse("2222-01-01T00:00:00Z"),
                    ),
                )

            val reservationDTO =
                ReservationDTO(
                    "1",
                    Status.PENDING,
                    "1",
                    "1",
                    "1",
                    "2221-01-01T00:00:00Z",
                )

            every { customerServiceMock.getCustomerById("1") } returns dummyCustomer1
            every { scheduleServiceMock.getScheduleById("1") } returns dummySchedule1
            every { serviceServiceMock.getServiceById("1") } returns dummyService1
            every { scheduleServiceMock.getScheduleById("1")?.reservations } returns reservations

            val isValid = service.validateReservation(reservationDTO)

            isValid shouldBe true
        }
    }
})

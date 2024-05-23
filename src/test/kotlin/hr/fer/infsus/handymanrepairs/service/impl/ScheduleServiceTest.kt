package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.repository.ScheduleRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ScheduleServiceTest : FunSpec({

    val scheduleRepositoryMock = mockk<ScheduleRepository>()
    val service = ScheduleService(scheduleRepositoryMock)

    context("getSchedules") {
        test("should return all schedules") {
            val expectedSchedules =
                listOf(
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
                    Schedule(
                        "2",
                        Handyman(
                            "2",
                            "Jane",
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
                )

            every { scheduleRepositoryMock.findAll() } returns expectedSchedules

            val result = service.getAllSchedules()

            result shouldBe expectedSchedules
        }

        test("should return schedule by id") {
            val expectedSchedule =
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
                )

            every { scheduleRepositoryMock.findScheduleById("1") } returns expectedSchedule

            val result = service.getScheduleById("1")

            result shouldBe expectedSchedule
        }

        test("should return all schedule DTOs") {
            val expectedSchedules =
                listOf(
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
                    Schedule(
                        "2",
                        Handyman(
                            "2",
                            "Jane",
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
                )

            every { scheduleRepositoryMock.findAll() } returns expectedSchedules

            val result = service.getAllScheduleDTOs()

            result shouldBe expectedSchedules.map { it.toDTO() }
        }

        test("should return schedule DTO by id") {
            val expectedSchedule =
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
                )

            every { scheduleRepositoryMock.findScheduleById("1") } returns expectedSchedule

            val result = service.getScheduleDTOById("1")

            result shouldBe expectedSchedule.toDTO()
        }

        test("should return null for non existing id") {
            every { scheduleRepositoryMock.findScheduleById("1") } returns null

            val result = service.getScheduleDTOById("1")

            result shouldBe null
        }
    }
})

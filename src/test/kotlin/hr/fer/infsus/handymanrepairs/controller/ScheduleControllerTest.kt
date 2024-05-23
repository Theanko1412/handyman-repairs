package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.IScheduleService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityNotFoundException

class ScheduleControllerTest : FunSpec({

    val serviceMock = mockk<IScheduleService>()
    val controller = ScheduleController(serviceMock)

    val scheduleDaos =
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

    context("get") {
        test("get all") {
            every { serviceMock.getAllScheduleDTOs() } returns scheduleDaos.map { it.toDTO() }

            val result = controller.getAllSchedules()

            result shouldBe scheduleDaos.map { it.toDTO() }
        }

        test("get by id") {
            every { serviceMock.getScheduleDTOById("1") } returns scheduleDaos[0].toDTO()

            val result = controller.getScheduleById("1")

            result shouldBe scheduleDaos[0].toDTO()
        }

        test("get by id throws exception when none found") {
            every { serviceMock.getScheduleDTOById("3") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getScheduleById("3")
                }

            exception.message shouldBe "Schedule with id 3 not found"
        }
    }
})

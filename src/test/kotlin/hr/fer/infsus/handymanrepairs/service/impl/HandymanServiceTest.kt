package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.repository.HandymanRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class HandymanServiceTest : FunSpec({

    val repositoryMock = mockk<HandymanRepository>()
    val service = HandymanService(repositoryMock)

    context("getHandymen") {
        test("should return all handymen") {
            val expectedHandymen =
                listOf(
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
                )

            every { repositoryMock.findAll() } returns expectedHandymen

            val handymen = service.getAllHandymen()

            handymen shouldBe expectedHandymen
        }

        test("should return handyman by id") {
            val expectedHandyman =
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

            every { repositoryMock.findHandymanById("1") } returns expectedHandyman

            val handyman = service.getHandymanById("1")

            handyman shouldBe expectedHandyman
        }

        test("should return handyman by email") {
            val expectedHandyman =
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

            every { repositoryMock.findHandymanByEmail("email") } returns expectedHandyman

            val handyman = service.getHandymanByEmail("email")

            handyman shouldBe expectedHandyman
        }

        test("should return handyman by schedule") {
            val expectedHandyman =
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

            val mockedSchedule =
                Schedule(
                    "123",
                    expectedHandyman,
                    emptyList(),
                )

            every { repositoryMock.findHandymanBySchedule(mockedSchedule) } returns expectedHandyman

            val handyman = service.getHandymanBySchedule(mockedSchedule)

            handyman shouldBe expectedHandyman
        }
    }

    context("addHandyman") {
        test("should add handyman") {
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

            every { repositoryMock.save(handyman) } returns handyman

            val addedHandyman = service.addHandyman(handyman)

            addedHandyman shouldBe handyman
        }
    }
})

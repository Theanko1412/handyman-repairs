package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dto.HandymanDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.service.IHandymanService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityNotFoundException

class HandymanControllerTest : FunSpec({

    val serviceMock = mockk<IHandymanService>()
    val controller = HandymanController(serviceMock)

    val expectedHandymen =
        listOf(
            HandymanDTO(
                "1",
                "John",
                "Doe",
                "email",
                CustomerType.HANDYMAN.toString(),
                "***",
                2.0,
                false,
                null,
                emptyList(),
                emptyList(),
                null,
            ),
            HandymanDTO(
                "2",
                "Jane",
                "Doe",
                "email",
                CustomerType.HANDYMAN.toString(),
                "***",
                2.0,
                false,
                null,
                emptyList(),
                emptyList(),
                null,
            ),
        )

    context("get") {
        test("get all") {
            every { serviceMock.getAllHandymen() } returns expectedHandymen.map { it.toDAO() }

            val result = controller.getHandymen()

            result shouldBe expectedHandymen
        }

        test("get by id") {
            every { serviceMock.getHandymanById("1") } returns expectedHandymen[0].toDAO()

            val result = controller.getHandyman("1")

            result shouldBe expectedHandymen[0]
        }

        test("get by id throws exception") {

            every { serviceMock.getHandymanById("3") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getHandyman("3")
                }

            exception.message shouldBe "Handyman with id 3 does not exist"
        }

        test("get by id with email") {
            every { serviceMock.getHandymanByEmail("email@email") } returns expectedHandymen[0].toDAO()

            val result = controller.getHandyman("email@email")

            result shouldBe expectedHandymen[0]
        }

        test("get by id with email throws exception") {

            every { serviceMock.getHandymanByEmail("email@email") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getHandyman("email@email")
                }

            exception.message shouldBe "Handyman with email email@email does not exist"
        }
    }
})

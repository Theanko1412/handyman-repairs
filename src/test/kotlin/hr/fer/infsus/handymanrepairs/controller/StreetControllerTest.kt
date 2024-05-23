package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dao.Street
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.impl.StreetService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityNotFoundException

class StreetControllerTest : FunSpec({

    val serviceMock = mockk<StreetService>()
    val controller = StreetController(serviceMock)

    val serviceDaos =
        listOf(
            Street(
                "1",
                "Street1",
                1,
                City(
                    "1",
                    "City1",
                    Country(
                        "1",
                        "Country1",
                        emptyList(),
                    ),
                    emptyList(),
                ),
                emptyList(),
            ),
            Street(
                "2",
                "Street2",
                2,
                City(
                    "2",
                    "City2",
                    Country(
                        "2",
                        "Country2",
                        emptyList(),
                    ),
                    emptyList(),
                ),
                emptyList(),
            ),
        )

    context("get") {
        test("get all") {
            every { serviceMock.getAllStreets() } returns serviceDaos

            val result = controller.getAllStreets()

            result shouldBe serviceDaos.map { it.toDTO() }
        }

        test("get by id") {
            val id = "1"
            val dao = serviceDaos[0]
            every { serviceMock.getStreetById(id) } returns dao

            val result = controller.getStreetById(id)

            result shouldBe dao.toDTO()
        }

        test("get by id throws exception") {
            every { serviceMock.getStreetById("1") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getStreetById("1")
                }

            exception.message shouldBe "Street with id 1 not found"
        }
    }
})

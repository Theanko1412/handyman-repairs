package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.ICityService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityNotFoundException

class CityControllerTest : FunSpec({

    val serviceMock = mockk<ICityService>()
    val controller = CityController(serviceMock)

    val cityDaos =
        listOf(
            City("1", "Zagreb", Country("1", "Croatia", emptyList()), emptyList()),
            City("2", "Split", Country("1", "Croatia", emptyList()), emptyList()),
        )

    val expectedCities = cityDaos.map(City::toDTO)

    context("get") {
        test("get all") {
            every { serviceMock.getAllCities() } returns cityDaos

            val result = controller.getAllCities()

            result shouldBe expectedCities
        }

        test("get by id") {
            val id = "1"
            val expectedCity = expectedCities.first()

            every { serviceMock.getCityById(id) } returns cityDaos.first()

            val result = controller.getCityById(id)

            result shouldBe expectedCity
        }

        test("get by id should throw exception") {
            every { serviceMock.getCityById("1") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getCityById("1")
                }

            exception.message shouldBe "City with id 1 not found"
        }
    }
})

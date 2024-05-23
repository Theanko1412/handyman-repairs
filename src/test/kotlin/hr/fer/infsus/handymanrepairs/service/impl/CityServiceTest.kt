package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.repository.CityRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CityServiceTest : FunSpec({

    val repositoryMock = mockk<CityRepository>()
    val service = CityService(repositoryMock)

    context("getCities") {
        test("should return all cities") {
            val expectedCities =
                listOf(
                    City("1", "Zagreb", Country("1", "Croatia", emptyList()), emptyList()),
                    City("2", "Split", Country("1", "Croatia", emptyList()), emptyList()),
                )

            every { repositoryMock.findAll() } returns expectedCities

            val cities = service.getAllCities()

            cities shouldContainExactly expectedCities
        }

        test("should return city by id") {
            val expectedCity = City("1", "Zagreb", Country("1", "Croatia", emptyList()), emptyList())

            every { repositoryMock.findCityById("1") } returns expectedCity

            val city = service.getCityById("1")

            city shouldBe expectedCity
        }
    }
})

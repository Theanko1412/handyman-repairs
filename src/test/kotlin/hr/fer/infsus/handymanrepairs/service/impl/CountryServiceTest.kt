package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.repository.CountryRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CountryServiceTest : FunSpec({

    val repositoryMock = mockk<CountryRepository>()
    val service = CountryService(repositoryMock)

    context("getCountries") {
        test("should return all countries") {
            val expectedCountries =
                listOf(
                    Country("1", "Croatia", emptyList()),
                    Country("2", "Serbia", emptyList()),
                )

            every { repositoryMock.findAll() } returns expectedCountries

            val countries = service.getAllCountries()

            countries shouldContainExactly expectedCountries
        }

        test("should return country by id") {
            val expectedCountry = Country("1", "Croatia", emptyList())

            every { repositoryMock.findCountryById("1") } returns expectedCountry

            val country = service.getCountryById("1")

            country shouldBe expectedCountry
        }
    }
})

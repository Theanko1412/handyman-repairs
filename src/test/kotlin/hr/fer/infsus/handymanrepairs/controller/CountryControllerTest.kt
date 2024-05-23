package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.CountryDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.service.ICountryService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityNotFoundException

class CountryControllerTest : FunSpec({

    val serviceMock = mockk<ICountryService>()
    val controller = CountryController(serviceMock)

    val countryDtos =
        listOf(
            CountryDTO("1", "Croatia", emptyList()),
            CountryDTO("2", "Serbia", emptyList()),
        )

    context("get") {
        test("get all") {
            every { serviceMock.getAllCountries() } returns countryDtos.map { it.toDAO(emptyList()) }

            val result = controller.getAllCountries()

            result shouldBe countryDtos
        }

        test("get by id") {
            val id = "1"
            val expectedCountry = countryDtos.first()

            every { serviceMock.getCountryById(id) } returns countryDtos.first().toDAO(emptyList())

            val result = controller.getCountryById(id)

            result shouldBe expectedCountry
        }

        test("get by id should throw exception") {
            every { serviceMock.getCountryById("3") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getCountryById("3")
                }

            exception.message shouldBe "Country with id 3 not found"
        }
    }
})

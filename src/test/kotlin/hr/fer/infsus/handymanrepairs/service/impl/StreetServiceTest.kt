package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dao.Street
import hr.fer.infsus.handymanrepairs.repository.StreetRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class StreetServiceTest : FunSpec({

    val streetRepositoryMock = mockk<StreetRepository>()

    val streetService = StreetService(streetRepositoryMock)

    context("get streets") {
        test("get all streets") {
            val expectedStreets =
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

            every { streetRepositoryMock.findAll() } returns expectedStreets

            val result = streetService.getAllStreets()

            result shouldBe expectedStreets
        }

        test("get street by id") {
            val expectedStreet =
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
                )

            every { streetRepositoryMock.findStreetById("1") } returns expectedStreet

            val result = streetService.getStreetById("1")

            result shouldBe expectedStreet
        }
    }
})

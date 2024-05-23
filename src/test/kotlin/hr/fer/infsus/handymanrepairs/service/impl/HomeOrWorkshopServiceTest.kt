package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import hr.fer.infsus.handymanrepairs.model.dao.Street
import hr.fer.infsus.handymanrepairs.model.dto.EnrichedHomeOrWorkshopDTO
import hr.fer.infsus.handymanrepairs.repository.CityRepository
import hr.fer.infsus.handymanrepairs.repository.CountryRepository
import hr.fer.infsus.handymanrepairs.repository.HomeOrWorkshopRepository
import hr.fer.infsus.handymanrepairs.repository.StreetRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityNotFoundException

class HomeOrWorkshopServiceTest : FunSpec({

    val repositoryMock = mockk<HomeOrWorkshopRepository>()
    val streetRepositoryMock = mockk<StreetRepository>()
    val cityRepositoryMock = mockk<CityRepository>()
    val countryRepositoryMock = mockk<CountryRepository>()

    val streetService = StreetService(streetRepositoryMock)
    val cityService = CityService(cityRepositoryMock)
    val countryService = CountryService(countryRepositoryMock)
    val service =
        HomeOrWorkshopService(
            repositoryMock,
            streetService,
            cityService,
            countryService,
        )

    context("getHomeOrWorkshops") {
        test("should return all enriched home or workshops") {
            val expectedEnrichedList =
                listOf(
                    EnrichedHomeOrWorkshopDTO(
                        "1",
                        "home",
                        "streetName",
                        1,
                        "cityName",
                        "countryName",
                    ),
                    EnrichedHomeOrWorkshopDTO(
                        "2",
                        "workshop",
                        "streetName",
                        1,
                        "cityName",
                        "countryName",
                    ),
                )

            val homeOrWorkshops =
                listOf(
                    HomeOrWorkshop(
                        "1",
                        "home",
                        Street(
                            "1",
                            "streetName",
                            1,
                            City(
                                "1",
                                "cityName",
                                Country(
                                    "1",
                                    "countryName",
                                    emptyList(),
                                ),
                                emptyList(),
                            ),
                            emptyList(),
                        ),
                    ),
                    HomeOrWorkshop(
                        "2",
                        "workshop",
                        Street(
                            "1",
                            "streetName",
                            1,
                            City(
                                "1",
                                "cityName",
                                Country(
                                    "1",
                                    "countryName",
                                    emptyList(),
                                ),
                                emptyList(),
                            ),
                            emptyList(),
                        ),
                    ),
                )

            every { repositoryMock.findAll() } returns homeOrWorkshops

            val result = service.getAllHomeOrWorkshops()

            result shouldBe expectedEnrichedList
        }

        test("should return homeOrWorkshop by id") {
            val expectedHomeOrWorkshop =
                HomeOrWorkshop(
                    "1",
                    "home",
                    Street(
                        "1",
                        "streetName",
                        1,
                        City(
                            "1",
                            "cityName",
                            Country(
                                "1",
                                "countryName",
                                emptyList(),
                            ),
                            emptyList(),
                        ),
                        emptyList(),
                    ),
                )

            every { repositoryMock.findHomeOrWorkshopById("1") } returns expectedHomeOrWorkshop

            val result = service.getHomeOrWorkshopById("1")

            result shouldBe expectedHomeOrWorkshop
        }

        test("should return enriched homeOrWorkshop by id") {
            val expectedEnrichedHomeOrWorkshop =
                EnrichedHomeOrWorkshopDTO(
                    "1",
                    "home",
                    "streetName",
                    1,
                    "cityName",
                    "countryName",
                )

            val homeOrWorkshop =
                HomeOrWorkshop(
                    "1",
                    "home",
                    Street(
                        "1",
                        "streetName",
                        1,
                        City(
                            "1",
                            "cityName",
                            Country(
                                "1",
                                "countryName",
                                emptyList(),
                            ),
                            emptyList(),
                        ),
                        emptyList(),
                    ),
                )

            every { repositoryMock.findHomeOrWorkshopById("1") } returns homeOrWorkshop

            val result = service.getEnrichedHomeOrWorkshopById("1")

            result shouldBe expectedEnrichedHomeOrWorkshop
        }

        test("should throw exception for invalid id") {
            every { repositoryMock.findHomeOrWorkshopById("1") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    service.getEnrichedHomeOrWorkshopById("1")
                }

            exception.message shouldBe "Home or workshop with id 1 not found"
        }
    }
})

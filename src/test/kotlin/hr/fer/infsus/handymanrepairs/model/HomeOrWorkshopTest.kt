package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import hr.fer.infsus.handymanrepairs.model.dao.Street
import hr.fer.infsus.handymanrepairs.model.dto.HomeOrWorkshopDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HomeOrWorkshopTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val dao =
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

            val dto = dao.toDTO()

            dto.id shouldBe "1"
            dto.name shouldBe "home"
            dto.streetId shouldBe "1"
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val dto =
                HomeOrWorkshopDTO(
                    "1",
                    "home",
                    "1",
                )

            val dao =
                dto.toDAO(
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

            dao.id shouldBe "1"
            dao.name shouldBe "home"
            dao.street.id shouldBe "1"
        }
    }
})

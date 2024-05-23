package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dao.Street
import hr.fer.infsus.handymanrepairs.model.dto.StreetDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class StreetTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val dao =
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

            val dto = dao.toDTO()

            dto.id shouldBe dao.id
            dto.name shouldBe dao.name
            dto.number shouldBe dao.number
            dto.cityId shouldBe dao.city.id
            dto.homeOrWorkshopIds shouldBe dao.homeOrWorkshops.map { it.id }
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val dto =
                StreetDTO(
                    "1",
                    "Street1",
                    1,
                    "1",
                    emptyList(),
                )

            val dao =
                dto.toDAO(
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

            dao.id shouldBe dto.id
            dao.name shouldBe dto.name
            dao.number shouldBe dto.number
            dao.city.id shouldBe dto.cityId
            dao.homeOrWorkshops shouldBe emptyList()
        }
    }
})

package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dto.CityDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CityTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val city = City("1", "Zagreb", Country("1", "Croatia", emptyList()), emptyList())

            val cityDto = city.toDTO()

            cityDto.id shouldBe "1"
            cityDto.name shouldBe "Zagreb"
            cityDto.countryId shouldBe "1"
            cityDto.streetIds shouldBe emptyList()
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val cityDto = CityDTO("1", "Zagreb", "1", emptyList())

            val city =
                cityDto.toDAO(
                    Country("1", "Croatia", emptyList()),
                    emptyList(),
                )

            city.id shouldBe "1"
            city.name shouldBe "Zagreb"
            city.country.id shouldBe "1"
            city.streets shouldBe emptyList()
        }
    }
})

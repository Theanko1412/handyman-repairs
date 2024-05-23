package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dto.CountryDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CountryTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val country = Country("1", "Croatia", emptyList())

            val countryDto = country.toDTO()

            countryDto.id shouldBe "1"
            countryDto.name shouldBe "Croatia"
            countryDto.cityIds shouldBe emptyList()
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val countryDto = CountryDTO("1", "Croatia", emptyList())

            val country = countryDto.toDAO(emptyList())

            country.id shouldBe "1"
            country.name shouldBe "Croatia"
            country.cities shouldBe emptyList()
        }
    }
})

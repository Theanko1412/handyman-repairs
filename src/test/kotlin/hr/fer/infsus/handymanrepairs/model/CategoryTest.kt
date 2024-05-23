package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.model.dto.CategoryDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CategoryTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val categoryDao =
                Category(
                    id = "1",
                    name = "Category 1",
                    description = "Description 1",
                    services = emptyList(),
                )

            val categoryDto = categoryDao.toDTO()

            categoryDto.id shouldBe "1"
            categoryDto.name shouldBe "Category 1"
            categoryDto.description shouldBe "Description 1"
            categoryDto.serviceIds shouldBe emptyList()
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val categoryDto =
                CategoryDTO(
                    id = "1",
                    name = "Category 1",
                    description = "Description 1",
                    serviceIds = emptyList(),
                )

            val categoryDao = categoryDto.toDAO(emptyList())

            categoryDao.id shouldBe "1"
            categoryDao.name shouldBe "Category 1"
            categoryDao.description shouldBe "Description 1"
            categoryDao.services shouldBe emptyList()
        }
    }
})

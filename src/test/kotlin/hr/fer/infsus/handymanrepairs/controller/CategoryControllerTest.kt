package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.CategoryDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.service.ICategoryService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityNotFoundException

class CategoryControllerTest : FunSpec({

    val serviceMock = mockk<ICategoryService>()
    val controller = CategoryController(serviceMock)

    val expectedCategories =
        listOf(
            CategoryDTO(
                id = "1",
                name = "Category 1",
                description = "Description 1",
                serviceIds = emptyList(),
            ),
            CategoryDTO(
                id = "2",
                name = "Category 2",
                description = "Description 2",
                serviceIds = emptyList(),
            ),
        )

    context("get") {
        test("get all") {

            every { serviceMock.getAllCategories() } returns expectedCategories.map { it.toDAO(emptyList()) }

            val result = controller.getCategories()

            result shouldBe expectedCategories
        }

        test("get by id") {
            val id = "1"
            val expectedCategory = expectedCategories.first()

            every { serviceMock.getCategoryById(id) } returns expectedCategory.toDAO(emptyList())

            val result = controller.getCategory(id)

            result shouldBe expectedCategory
        }

        test("get by id not found exception") {

            every { serviceMock.getCategoryById("1") } returns null

            val exception =
                shouldThrow<EntityNotFoundException> {
                    controller.getCategory("1")
                }

            exception.message shouldBe "Category with id 1 not found"
        }
    }
})

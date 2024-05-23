package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.repository.CategoryRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk

class CategoryServiceTest : FunSpec({

    val repositoryMock = mockk<CategoryRepository>()
    val service = CategoryService(repositoryMock)

    context("getCategories") {
        test("should return all categories") {
            val expectedCategories =
                listOf(
                    Category(
                        id = "1",
                        name = "Category 1",
                        description = "Description 1",
                        services = emptyList(),
                    ),
                    Category(
                        id = "2",
                        name = "Category 2",
                        description = "Description 2",
                        services = emptyList(),
                    ),
                )

            every { repositoryMock.findAll() } returns expectedCategories

            val result = service.getAllCategories()

            result shouldBe expectedCategories
        }

        test("should return category by id") {
            val expectedCategory =
                Category(
                    id = "1",
                    name = "Category 1",
                    description = "Description 1",
                    services = emptyList(),
                )
            coEvery { repositoryMock.findCategoryById("1") } returns expectedCategory

            val result = service.getCategoryById("1")

            result shouldBe expectedCategory
        }

        test("should return category by name") {
            val expectedCategory =
                Category(
                    id = "1",
                    name = "Category 1",
                    description = "Description 1",
                    services = emptyList(),
                )
            coEvery { repositoryMock.findCategoryByName("Category 1") } returns expectedCategory

            val result = service.getCategoryByName("Category 1")

            result shouldBe expectedCategory
        }
    }
})

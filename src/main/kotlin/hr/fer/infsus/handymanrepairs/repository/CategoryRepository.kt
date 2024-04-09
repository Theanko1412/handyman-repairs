package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, String> {
    fun findCategoryById(id: String): Category?

    fun findCategoryByName(name: String): Category?

    fun findCategoriesByServicesId(serviceId: String): List<Category>

    fun deleteCategoryById(id: String)

    fun deleteCategoryByName(name: String)
}

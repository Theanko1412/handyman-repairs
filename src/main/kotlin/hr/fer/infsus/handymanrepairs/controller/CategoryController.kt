package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.CategoryDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.ICategoryService
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category", produces = ["application/json"])
class CategoryController(
    @Autowired
    private val categoryService: ICategoryService,
) {
    @GetMapping
    fun getCategories(): List<CategoryDTO>? {
        return categoryService.getAllCategories().map { it.toDTO() }
    }

    @GetMapping("/{id}")
    fun getCategory(
        @PathVariable id: String,
    ): CategoryDTO {
        return categoryService.getCategoryById(id)?.toDTO()
            ?: throw EntityNotFoundException("Category with id $id not found")
    }
}

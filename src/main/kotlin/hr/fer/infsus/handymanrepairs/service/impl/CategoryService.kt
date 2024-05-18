package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.repository.CategoryRepository
import hr.fer.infsus.handymanrepairs.service.ICategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService(
    @Autowired
    private val categoryRepository: CategoryRepository,
) : ICategoryService {
    override fun getCategoryById(id: String): Category? {
        return categoryRepository.findCategoryById(id)
    }

    override fun getCategoryByName(name: String): Category? {
        return categoryRepository.findCategoryByName(name)
    }

    override fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }
}

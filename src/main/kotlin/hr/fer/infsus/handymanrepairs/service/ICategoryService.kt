package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.Category

interface ICategoryService {
    fun getCategoryById(id: String): Category?

    fun getCategoryByName(name: String): Category?

    fun getAllCategories(): List<Category>
}

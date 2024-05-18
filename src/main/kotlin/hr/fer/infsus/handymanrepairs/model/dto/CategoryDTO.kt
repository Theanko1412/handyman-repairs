package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.model.dao.Service

data class CategoryDTO(
    var id: String? = null,
    val name: String,
    val description: String,
    val serviceIds: List<String>,
)

fun Category.toDTO() =
    CategoryDTO(
        id = this.id,
        name = this.name,
        description = this.description,
        serviceIds = this.services.map { it.id!! },
    )

fun CategoryDTO.toDAO(services: List<Service>) =
    Category(
        id = this.id,
        name = this.name,
        description = this.description,
        services = services,
    )

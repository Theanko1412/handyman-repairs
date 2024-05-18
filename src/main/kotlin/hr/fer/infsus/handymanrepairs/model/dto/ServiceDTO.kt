package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import hr.fer.infsus.handymanrepairs.model.dao.Service

data class ServiceDTO(
    var id: String? = null,
    var name: String,
    var description: String,
    var price: Double,
    var duration: Int,
    var categoryId: String,
    var handymanId: String,
    val reservationIds: MutableList<String>? = null,
)

fun Service.toDTO() =
    ServiceDTO(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
        duration = this.duration,
        categoryId = this.category.id!!,
        handymanId = this.handyman.id!!,
        reservationIds = this.reservations.map { it.id!! }.toMutableList(),
    )

fun ServiceDTO.toDAO(
    category: Category,
    handyman: Handyman,
    reservations: List<Reservation>,
) = Service(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    duration = this.duration,
    category = category,
    handyman = handyman,
    reservations = reservations.toMutableList(),
)

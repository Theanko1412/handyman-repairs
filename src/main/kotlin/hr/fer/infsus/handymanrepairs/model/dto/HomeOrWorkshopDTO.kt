package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import hr.fer.infsus.handymanrepairs.model.dao.Street

data class HomeOrWorkshopDTO(
    var id: String? = null,
    val streetId: String,
)

fun HomeOrWorkshop.toDTO() =
    HomeOrWorkshopDTO(
        id = this.id,
        streetId = this.street.id!!,
    )

fun HomeOrWorkshopDTO.toDAO(street: Street) =
    HomeOrWorkshop(
        id = this.id,
        street = street,
    )

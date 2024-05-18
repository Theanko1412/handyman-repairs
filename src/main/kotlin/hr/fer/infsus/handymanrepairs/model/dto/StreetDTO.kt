package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import hr.fer.infsus.handymanrepairs.model.dao.Street

data class StreetDTO(
    var id: String? = null,
    val name: String,
    val cityId: String,
    val homeOrWorkshopIds: List<String>,
)

fun Street.toDTO() =
    StreetDTO(
        id = this.id,
        name = this.name,
        cityId = this.city.id!!,
        homeOrWorkshopIds = this.homeOrWorkshops.map { it.id!! },
    )

fun StreetDTO.toDAO(
    city: City,
    homeOrWorkshops: List<HomeOrWorkshop>,
) = Street(
    id = this.id,
    name = this.name,
    city = city,
    homeOrWorkshops = homeOrWorkshops,
)

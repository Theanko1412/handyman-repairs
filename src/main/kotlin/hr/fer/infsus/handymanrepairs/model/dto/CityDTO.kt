package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dao.Street

data class CityDTO(
    var id: String? = null,
    val name: String,
    val countryId: String,
    val streetIds: List<String>,
)

fun City.toDTO() =
    CityDTO(
        id = this.id,
        name = this.name,
        countryId = this.country.id!!,
        streetIds = this.streets.map { it.id!! },
    )

fun CityDTO.toDAO(
    country: Country,
    streets: List<Street>,
) = City(
    id = this.id,
    name = this.name,
    country = country,
    streets = streets,
)

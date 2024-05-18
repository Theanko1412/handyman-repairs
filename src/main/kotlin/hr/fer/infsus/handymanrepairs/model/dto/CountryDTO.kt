package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dao.Country

data class CountryDTO(
    var id: String? = null,
    val name: String,
    val cityIds: List<String>,
)

fun Country.toDTO() =
    CountryDTO(
        id = this.id,
        name = this.name,
        cityIds = this.cities.map { it.id!! },
    )

fun CountryDTO.toDAO(cities: List<City>) =
    Country(
        id = this.id,
        name = this.name,
        cities = cities,
    )

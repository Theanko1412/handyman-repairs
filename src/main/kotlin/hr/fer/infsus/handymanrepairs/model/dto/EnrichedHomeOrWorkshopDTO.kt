package hr.fer.infsus.handymanrepairs.model.dto

data class EnrichedHomeOrWorkshopDTO(
    val id: String,
    val name: String,
    val streetName: String,
    val streetNumber: Int,
    val cityName: String,
    val countryName: String,
)

package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.City
import org.springframework.data.jpa.repository.JpaRepository

interface CityRepository : JpaRepository<City, String> {
    fun findCityById(id: String): City?
    fun findCityByName(name: String): City?
    fun findCitiesByCountryId(countryId: String): List<City>

    fun deleteCityById(id: String)
    fun deleteCityByName(name: String)

}
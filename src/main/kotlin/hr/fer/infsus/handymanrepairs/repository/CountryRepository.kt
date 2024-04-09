package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Country
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository : JpaRepository<Country, String> {
    fun findCountryById(id: String): Country?
    fun findCountryByName(name: String): Country?

    fun deleteCountryById(id: String)
    fun deleteCountryByName(name: String)

}
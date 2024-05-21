package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.Country

interface ICountryService {
    fun getAllCountries(): List<Country>

    fun getCountryById(id: String): Country?
}

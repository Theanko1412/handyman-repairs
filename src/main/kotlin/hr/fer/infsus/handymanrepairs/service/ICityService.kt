package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.City

interface ICityService {
    fun getAllCities(): List<City>

    fun getCityById(id: String): City?
}

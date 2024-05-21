package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.Street

interface IStreetService {
    fun getAllStreets(): List<Street>

    fun getStreetById(id: String): Street?
}

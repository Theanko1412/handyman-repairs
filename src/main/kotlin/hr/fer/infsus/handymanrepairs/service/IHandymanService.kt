package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.Handyman

interface IHandymanService {
    fun getHandymanById(id: String): Handyman?

    fun getAllHandymen(): List<Handyman>

    fun addHandyman(handymen: Handyman): Handyman

    fun getHandymanByEmail(email: String): Handyman?
}

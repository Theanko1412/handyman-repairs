package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import hr.fer.infsus.handymanrepairs.model.dto.EnrichedHomeOrWorkshopDTO

interface IHomeOrWorkshopService {
    fun getAllHomeOrWorkshops(): List<EnrichedHomeOrWorkshopDTO>

    fun getHomeOrWorkshopById(id: String): HomeOrWorkshop?

    fun getEnrichedHomeOrWorkshopById(id: String): EnrichedHomeOrWorkshopDTO
}

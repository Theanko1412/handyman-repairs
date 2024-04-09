package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import org.springframework.data.jpa.repository.JpaRepository

interface HomeOrWorkshopRepository : JpaRepository<HomeOrWorkshop, String> {
    fun findHomeOrWorkshopById(id: String): HomeOrWorkshop?

    fun deleteHomeOrWorkshopById(id: String)
}

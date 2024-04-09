package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Street
import org.springframework.data.jpa.repository.JpaRepository

interface StreetRepository : JpaRepository<Street, String> {
    fun findStreetById(id: String): Street?

    fun findStreetByName(name: String): Street?

    fun deleteStreetById(id: String)
}

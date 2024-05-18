package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Service
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceRepository : JpaRepository<Service, String> {
    fun findServiceById(id: String): Service?

    fun findServicesByCategoryId(categoryId: String): List<Service>

    fun findServicesByHandymanId(handymanId: String): List<Service>

    fun deleteServiceById(id: String)
}

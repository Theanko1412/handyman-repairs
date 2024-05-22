package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import org.springframework.data.jpa.repository.JpaRepository

interface HandymanRepository : JpaRepository<Handyman, String> {
    fun findHandymanById(id: String): Handyman?

    fun findHandymanByEmail(email: String): Handyman?

    fun findHandymanBySchedule(schedule: Schedule): Handyman?

    fun findHandymanByFirstName(firstName: String): Handyman?

    fun findHandymanByLastName(lastName: String): Handyman?

    fun findHandymenByIsSuspended(isSuspended: Boolean): List<Handyman>

    fun deleteHandymanById(id: String)

    fun deleteHandymanByEmail(email: String)
}

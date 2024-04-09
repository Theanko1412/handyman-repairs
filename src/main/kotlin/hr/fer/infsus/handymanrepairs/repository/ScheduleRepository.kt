package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository : JpaRepository<Schedule, String> {
    fun findScheduleById(id: String): Schedule?
    fun findSchedulesByHandymanId(handymanId: String): List<Schedule>

    fun deleteScheduleById(id: String)

}
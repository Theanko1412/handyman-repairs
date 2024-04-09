package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, String> {
    fun findReservationById(id: String): Reservation?

    fun findReservationsByScheduleId(scheduleId: String): List<Reservation>

    fun deleteReservationById(id: String)
}

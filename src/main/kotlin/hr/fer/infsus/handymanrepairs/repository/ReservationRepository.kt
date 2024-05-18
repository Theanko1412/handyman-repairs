package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import hr.fer.infsus.handymanrepairs.model.dao.Status
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, String> {
    fun findReservationById(id: String): Reservation?

    fun findReservationsByScheduleId(scheduleId: String): List<Reservation>

    fun findReservationsByScheduleIdAndStatus(
        scheduleId: String,
        status: Status,
    ): List<Reservation>

    fun findReservationsByCustomerId(customerId: String): List<Reservation>

    fun findReservationsByCustomerIdAndStatus(
        customerId: String,
        status: Status,
    ): List<Reservation>

    fun findReservationsByStatus(status: Status): List<Reservation>

    fun deleteReservationById(id: String)
}

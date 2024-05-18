package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import hr.fer.infsus.handymanrepairs.model.dao.Status
import hr.fer.infsus.handymanrepairs.model.dto.ReservationDTO

interface IReservationService {
    fun getAllReservations(): List<Reservation>

    fun getReservationById(id: String): Reservation?

    fun getReservationsByScheduleId(scheduleId: String): List<Reservation>

    fun getReservationsByScheduleIdAndStatus(
        customerId: String,
        status: Status,
    ): List<Reservation>

    fun getReservationsByCustomerIdAndStatus(
        customerId: String,
        status: Status,
    ): List<Reservation>

    fun getReservationsByCustomerId(customerId: String): List<Reservation>

    fun getAllReservationsByStatus(status: Status): List<Reservation>

    fun addReservation(reservation: Reservation): Reservation

    fun validateReservation(reservation: ReservationDTO): Boolean

    fun updateReservationStatusById(
        id: String,
        status: Status,
    ): Reservation

    fun deleteReservationById(id: String)

    fun buildReservation(reservationDTO: ReservationDTO): Reservation
}

package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import hr.fer.infsus.handymanrepairs.model.dao.Status
import hr.fer.infsus.handymanrepairs.model.dto.ReservationDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.repository.ReservationRepository
import hr.fer.infsus.handymanrepairs.service.ICustomerService
import hr.fer.infsus.handymanrepairs.service.IReservationService
import hr.fer.infsus.handymanrepairs.service.IScheduleService
import hr.fer.infsus.handymanrepairs.service.IServiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class ReservationService(
    @Autowired
    private val reservationRepository: ReservationRepository,
    @Autowired
    private val scheduleService: IScheduleService,
    @Autowired
    private val serviceService: IServiceService,
    @Autowired
    private val customerService: ICustomerService,
) : IReservationService {
    override fun getAllReservations(): List<Reservation> {
        return reservationRepository.findAll()
    }

    override fun getReservationById(id: String): Reservation? {
        return reservationRepository.findReservationById(id)
    }

    override fun getReservationsByScheduleId(scheduleId: String): List<Reservation> {
        return reservationRepository.findReservationsByScheduleId(scheduleId)
    }

    override fun getReservationsByScheduleIdAndStatus(
        customerId: String,
        status: Status,
    ): List<Reservation> {
        return reservationRepository.findReservationsByScheduleIdAndStatus(customerId, status)
    }

    override fun getReservationsByCustomerIdAndStatus(
        customerId: String,
        status: Status,
    ): List<Reservation> {
        return reservationRepository.findReservationsByCustomerIdAndStatus(customerId, status)
    }

    override fun getReservationsByCustomerId(customerId: String): List<Reservation> {
        return reservationRepository.findReservationsByCustomerId(customerId)
    }

    override fun getAllReservationsByStatus(status: Status): List<Reservation> {
        return reservationRepository.findReservationsByStatus(status)
    }

    override fun addReservation(reservation: Reservation): Reservation {
        require(validateReservation(reservation.toDTO()))
        return reservationRepository.save(reservation)
    }

    override fun validateReservation(reservation: ReservationDTO): Boolean {
        require(reservation.customerId.isNotEmpty()) { "Customer ID must not be empty" }
        require(reservation.scheduleId.isNotEmpty()) { "Schedule ID must not be empty" }
        require(OffsetDateTime.parse(reservation.dateTime).isAfter(OffsetDateTime.now())) { "Reservation date must be in the future" }
        return true
    }

    override fun updateReservationStatusById(
        id: String,
        status: Status,
    ): Reservation {
        val reservation = reservationRepository.findReservationById(id)
        require(reservation != null) { "Reservation with ID $id not found" }
        reservation.status = status
        return reservationRepository.save(reservation)
    }

    override fun deleteReservationById(id: String) {
        reservationRepository.deleteReservationById(id)
    }

    override fun buildReservation(reservationDTO: ReservationDTO): Reservation {
        val schedule = scheduleService.getScheduleById(reservationDTO.scheduleId)
        val service = serviceService.getServiceById(reservationDTO.serviceId)
        val customer = customerService.getCustomerById(reservationDTO.customerId)

        require(schedule != null) { "Schedule with ID ${reservationDTO.scheduleId} not found" }
        require(service != null) { "Service with ID ${reservationDTO.serviceId} not found" }
        require(customer != null) { "Customer with ID ${reservationDTO.customerId} not found" }

        return Reservation(
            schedule = schedule,
            service = service,
            customer = customer,
            dateTime = OffsetDateTime.parse(reservationDTO.dateTime),
        )
    }
}

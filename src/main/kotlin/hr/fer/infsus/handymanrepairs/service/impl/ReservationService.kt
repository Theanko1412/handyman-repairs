package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import hr.fer.infsus.handymanrepairs.model.dao.Status
import hr.fer.infsus.handymanrepairs.model.dto.ReservationDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.repository.ReservationRepository
import hr.fer.infsus.handymanrepairs.service.ICustomerService
import hr.fer.infsus.handymanrepairs.service.IHandymanService
import hr.fer.infsus.handymanrepairs.service.IReservationService
import hr.fer.infsus.handymanrepairs.service.IScheduleService
import hr.fer.infsus.handymanrepairs.service.IServiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.ZoneId

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
    @Autowired
    private val handymanService: IHandymanService,
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
        require(reservation.serviceId.isNotEmpty()) { "Service ID must not be empty" }
        require(OffsetDateTime.parse(reservation.dateTime).isAfter(OffsetDateTime.now())) { "Reservation date must be in the future" }

        val customer = customerService.getCustomerById(reservation.customerId)
        val schedule = scheduleService.getScheduleById(reservation.scheduleId)
        val service = serviceService.getServiceById(reservation.serviceId)

        require(customer != null) { "Customer with ID ${reservation.customerId} not found" }
        require(schedule != null) { "Schedule with ID ${reservation.scheduleId} not found" }
        require(service != null) { "Service with ID ${reservation.serviceId} not found" }

        val refreshBuffer = 30
        val newReservationDateTime = OffsetDateTime.parse(reservation.dateTime)
        val newReservationEndTime = newReservationDateTime.plusMinutes(service.duration.toLong())

        val reservationStartHour = newReservationDateTime.hour
        val reservationEndHour = newReservationEndTime.hour
        val reservationEndMinute = newReservationEndTime.minute

        if (reservationStartHour < 7) {
            throw IllegalArgumentException("Reservation must start after 7 AM. Available times are from 7 AM onwards.")
        }

        if (reservationEndHour > 17 || (reservationEndHour == 17 && reservationEndMinute > 0)) {
            throw IllegalArgumentException("Reservation must end before 5 PM. Available times end at 5 PM.")
        }

        for (oldReservation in schedule.reservations) {
            val oldReservationStart = oldReservation.dateTime
            val oldReservationEnd = oldReservationStart.plusMinutes(oldReservation.service.duration.toLong()).plusMinutes(refreshBuffer.toLong())

            if (newReservationDateTime.isBefore(oldReservationEnd) && newReservationEndTime.isAfter(oldReservationStart)) {
                val availableAfter = oldReservationEnd.plusMinutes(refreshBuffer.toLong())
                val availableBefore = oldReservationStart.minusMinutes(service.duration.toLong()).minusMinutes(refreshBuffer.toLong())

                throw IllegalArgumentException("Reservation time is not available. Please choose a time after ${availableAfter.atZoneSameInstant(ZoneId.of("Europe/Zagreb"))} or before ${availableBefore.atZoneSameInstant(ZoneId.of("Europe/Zagreb"))}.")
            }
        }

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

package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.Status
import hr.fer.infsus.handymanrepairs.model.dto.ReservationDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.IReservationService
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reservation", produces = ["application/json"])
class ReservationController(
    @Autowired
    private val reservationService: IReservationService,
) {
    @GetMapping
    fun getReservations(
        @RequestParam(required = false) scheduleId: String? = null,
        @RequestParam(required = false) customerId: String? = null,
        @RequestParam(required = false) status: Status? = null,
    ): List<ReservationDTO> {
        require(!(scheduleId != null && customerId != null)) { "Only one filter can be used" }
        require(status in Status.entries.toTypedArray() || status == null) { "Invalid status, must be one of ${Status.entries}" }

        if (status != null) {
            if (scheduleId != null) {
                return reservationService.getReservationsByScheduleIdAndStatus(scheduleId, status).map { it.toDTO() }
            } else if (customerId != null) {
                return reservationService.getReservationsByCustomerIdAndStatus(customerId, status).map { it.toDTO() }
            }
        } else {
            if (scheduleId != null) {
                return reservationService.getReservationsByScheduleId(scheduleId).map { it.toDTO() }
            } else if (customerId != null) {
                return reservationService.getReservationsByCustomerId(customerId).map { it.toDTO() }
            }
        }

        return reservationService.getAllReservations().map { it.toDTO() }
    }

    @GetMapping("/{id}")
    fun getReservation(
        @PathVariable id: String,
        @RequestParam(required = false) status: Status? = null,
    ): ReservationDTO {
        return reservationService.getReservationById(id)?.toDTO()
            ?: throw EntityNotFoundException("Reservation with id $id not found")
    }

    @PostMapping
    fun addReservation(
        @Validated @RequestBody reservationDTO: ReservationDTO,
    ): ReservationDTO {
        val reservation = reservationService.buildReservation(reservationDTO)
        return reservationService.addReservation(reservation).toDTO()
    }

    @PatchMapping("/{id}")
    fun updateReservation(
        @PathVariable id: String,
        @RequestParam status: Status,
    ): ReservationDTO {
        require(status in Status.entries.toTypedArray()) { "Invalid status, must be one of ${Status.entries}" }
        return reservationService.updateReservationStatusById(id, status).toDTO()
    }
}

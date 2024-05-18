package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.model.dao.Service
import hr.fer.infsus.handymanrepairs.model.dao.Status
import java.time.OffsetDateTime

data class ReservationDTO(
    var id: String? = null,
    var status: Status = Status.PENDING,
    val customerId: String,
    val scheduleId: String,
    val serviceId: String,
    val dateTime: String,
)

fun Reservation.toDTO() =
    ReservationDTO(
        id = this.id,
        status = this.status,
        scheduleId = this.schedule.id!!,
        serviceId = this.service.id!!,
        dateTime = this.dateTime.toString(),
        customerId = this.customer.id!!,
    )

fun ReservationDTO.toDAO(
    schedule: Schedule,
    service: Service,
    customer: Customer,
) = Reservation(
    id = this.id,
    status = this.status,
    schedule = schedule,
    service = service,
    dateTime = OffsetDateTime.parse(this.dateTime),
    customer = customer,
)

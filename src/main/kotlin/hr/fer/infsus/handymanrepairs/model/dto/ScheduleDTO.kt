package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Reservation
import hr.fer.infsus.handymanrepairs.model.dao.Schedule

data class ScheduleDTO(
    var id: String? = null,
    val handymanId: String,
    val reservationIds: List<String>,
)

fun Schedule.toDTO() =
    ScheduleDTO(
        id = this.id,
        handymanId = this.handyman.id!!,
        reservationIds = this.reservations.map { it.id!! },
    )

fun ScheduleDTO.toDAO(
    handyman: Handyman,
    reservations: List<Reservation>,
) = Schedule(
    id = this.id,
    handyman = handyman,
    reservations = reservations,
)

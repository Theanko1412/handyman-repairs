package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import hr.fer.infsus.handymanrepairs.model.dao.Notification
import hr.fer.infsus.handymanrepairs.model.dao.Reservation

data class CustomerDTO(
    var id: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val strikes: Int,
    val isSuspended: Boolean,
    val homeOrWorkshopId: String,
    val notificationIds: List<String>,
    val reservationIds: List<String>,
)

fun Customer.toDTO() =
    CustomerDTO(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        strikes = this.strikes,
        isSuspended = this.isSuspended,
        homeOrWorkshopId = this.homeOrWorkshop.id!!,
        notificationIds = this.notifications.map { it.id!! },
        reservationIds = this.reservations.map { it.id!! },
    )

fun CustomerDTO.toDAO(
    homeOrWorkshop: HomeOrWorkshop,
    notifications: List<Notification>,
    reservations: List<Reservation>,
) = Customer(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    strikes = this.strikes,
    isSuspended = this.isSuspended,
    homeOrWorkshop = homeOrWorkshop,
    notifications = notifications,
    reservations = reservations,
)

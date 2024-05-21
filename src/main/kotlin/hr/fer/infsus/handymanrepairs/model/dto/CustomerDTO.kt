package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import hr.fer.infsus.handymanrepairs.model.dao.Notification
import hr.fer.infsus.handymanrepairs.model.dao.Reservation

data class CustomerDTO(
    var id: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val type: String = "CUSTOMER",
    val strikes: Int = 0,
    val isSuspended: Boolean = false,
    val homeOrWorkshopId: String,
    val notificationIds: List<String> = emptyList(),
    val reservationIds: List<String> = emptyList(),
)

fun Customer.toDTO() =
    CustomerDTO(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        type = this.type.name,
        strikes = this.strikes,
        isSuspended = this.isSuspended,
        homeOrWorkshopId = this.homeOrWorkshop.id!!,
        notificationIds = this.notifications.map { it.id!! },
        reservationIds = this.reservations.map { it.id!! },
        password = "***",
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
    type = CustomerType.CUSTOMER,
    strikes = this.strikes,
    isSuspended = this.isSuspended,
    homeOrWorkshop = homeOrWorkshop,
    notifications = notifications,
    reservations = reservations,
    customerPassword = this.password,
)

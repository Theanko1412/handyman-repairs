package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Notification
import hr.fer.infsus.handymanrepairs.model.dao.Service

data class HandymanDTO(
    var id: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val type: String = "HANDYMAN",
    val password: String,
    val rating: Double = 0.0,
    val isSuspended: Boolean = false,
    val homeOrWorkshopId: String? = null,
    val notificationIds: List<String> = emptyList(),
    val serviceIds: List<String> = emptyList(),
    val scheduleId: String? = null,
)

fun Handyman.toDTO() =
    HandymanDTO(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        rating = this.rating,
        type = this.type.name,
        isSuspended = this.isSuspended,
        homeOrWorkshopId = this.homeOrWorkshop?.id,
        notificationIds = this.notifications.map { it.id!! },
        serviceIds = this.services.map { it.id!! },
        scheduleId = this.schedule?.id,
        password = "***",
    )

fun HandymanDTO.toDAO(
    notifications: List<Notification> = emptyList(),
    services: List<Service> = emptyList(),
) = Handyman(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    type = CustomerType.HANDYMAN,
    rating = this.rating,
    isSuspended = this.isSuspended,
    homeOrWorkshop = null,
    notifications = notifications,
    services = services,
    schedule = null,
    handymanPassword = this.password,
)

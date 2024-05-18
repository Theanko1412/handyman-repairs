package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import hr.fer.infsus.handymanrepairs.model.dao.Notification
import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.model.dao.Service

data class HandymanDTO(
    var id: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val rating: Double,
    val isSuspended: Boolean,
    val homeOrWorkshopId: String,
    val notificationIds: List<String>,
    val serviceIds: List<String>,
    val scheduleId: String?,
)

fun Handyman.toDTO() =
    HandymanDTO(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        rating = this.rating,
        isSuspended = this.isSuspended,
        homeOrWorkshopId = this.homeOrWorkshop.id!!,
        notificationIds = this.notifications.map { it.id!! },
        serviceIds = this.services.map { it.id!! },
        scheduleId = this.schedule.id,
    )

fun HandymanDTO.toDAO(
    homeOrWorkshop: HomeOrWorkshop,
    notifications: List<Notification>,
    services: List<Service>,
    schedule: Schedule,
) = Handyman(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    rating = this.rating,
    isSuspended = this.isSuspended,
    homeOrWorkshop = homeOrWorkshop,
    notifications = notifications,
    services = services,
    schedule = schedule,
)

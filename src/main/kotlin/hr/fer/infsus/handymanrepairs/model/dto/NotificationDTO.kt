package hr.fer.infsus.handymanrepairs.model.dto

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Notification
import java.time.OffsetDateTime

data class NotificationDTO(
    var id: String? = null,
    val message: String,
    val date: OffsetDateTime,
    val sender: String,
    val customerId: String?,
    val handymanId: String?,
)

fun Notification.toDTO() =
    NotificationDTO(
        id = this.id,
        message = this.message,
        date = this.date,
        sender = this.sender,
        customerId = this.customer.id,
        handymanId = this.handyman.id,
    )

fun NotificationDTO.toDAO(
    customer: Customer,
    handyman: Handyman,
) = Notification(
    id = this.id,
    message = this.message,
    date = this.date,
    sender = this.sender,
    customer = customer,
    handyman = handyman,
)

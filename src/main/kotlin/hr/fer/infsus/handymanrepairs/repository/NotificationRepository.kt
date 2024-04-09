package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, String> {
    fun findNotificationById(id: String): Notification?

    fun findNotificationsByCustomer(customer: Customer): List<Notification>

    fun findNotificationsByHandyman(handyman: Handyman): List<Notification>

    fun deleteNotificationById(id: String)

    fun deleteNotificationsByCustomer(customer: Customer)

    fun deleteNotificationsByHandyman(handyman: Handyman)
}

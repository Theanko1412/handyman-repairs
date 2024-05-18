package hr.fer.infsus.handymanrepairs.model.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.time.OffsetDateTime

@Entity
@Table(name = "notification")
data class Notification(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "notification_id")
    var id: String? = null,
    @Column(name = "message")
    val message: String,
    @Column(name = "date")
    val date: OffsetDateTime,
    @Column(name = "sender")
    val sender: String,
    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer,
    @ManyToOne
    @JoinColumn(name = "handyman_id")
    val handyman: Handyman,
)

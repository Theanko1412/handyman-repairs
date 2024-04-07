package hr.fer.infsus.handymanrepairs.model.dao

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "reservation")
data class Reservation(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String,

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    val schedule: Schedule,

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    val service: Service,
)

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
@Table(name = "reservation")
data class Reservation(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    var id: String? = null,
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer,
    @Column(name = "status")
    var status: Status = Status.PENDING,
    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    val schedule: Schedule,
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    val service: Service,
    @Column(name = "date")
    val dateTime: OffsetDateTime,
)

enum class Status {
    PENDING,
    ACCEPTED,
    REJECTED,
    COMPLETED,
    CANCELLED,
}

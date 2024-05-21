package hr.fer.infsus.handymanrepairs.model.dao

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "schedule")
data class Schedule(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "schedule_id")
    var id: String? = null,
    @OneToOne
    @JoinColumn(name = "handyman_id")
    var handyman: Handyman? = null,
    @OneToMany(mappedBy = "schedule", cascade = [CascadeType.REMOVE])
    @Column(name = "reservations")
    val reservations: List<Reservation>,
)

package hr.fer.infsus.handymanrepairs.model.dao

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "handyman")
data class Handyman(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "handyman_id")
    val id: String,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "rating")
    val rating: Double,
    @Column(name = "is_suspended")
    val isSuspended: Boolean,

    @ManyToOne
    @JoinColumn(name = "homeOrWorkshop_id")
    val homeOrWorkshop: HomeOrWorkshop,

    @OneToMany(mappedBy = "handyman", cascade = [CascadeType.REMOVE])
    @Column(name = "notifications")
    val notifications: List<Notification>,

    @ManyToMany
    @JoinTable(
        name = "handyman_services",
        joinColumns = [JoinColumn(name = "handyman_id")],
        inverseJoinColumns = [JoinColumn(name = "service_id")]
    )
    val services: List<Service>,

    @OneToOne(mappedBy = "handyman")
    val schedule: Schedule,
)
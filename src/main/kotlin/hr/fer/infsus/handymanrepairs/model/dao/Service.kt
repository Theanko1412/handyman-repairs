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
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "service")
data class Service(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "service_id")
    val id: String,
    @Column(name = "name")
    val name: String,
    @Column(name = "description")
    val description: String,
    @Column(name = "price")
    val price: Double,
    @Column(name = "duration")
    val duration: Int,
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category,

    @ManyToMany
    @JoinTable(
        name = "service_handymans",
        joinColumns = [JoinColumn(name = "service_id")],
        inverseJoinColumns = [JoinColumn(name = "handyman_id")]
    )
    val handymans: List<Handyman>,

    @OneToMany(mappedBy = "service", cascade = [CascadeType.REMOVE])
    @Column(name = "reservations")
    val reservations: List<Reservation>
)

package hr.fer.infsus.handymanrepairs.model.dao

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
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
    var id: String? = null,
    @Column(name = "name")
    var name: String,
    @Column(name = "description")
    var description: String,
    @Column(name = "price")
    var price: Double,
    @Column(name = "duration")
    var duration: Int,
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category,
    @ManyToOne
    @JoinColumn(name = "handyman_id", nullable = false)
    var handyman: Handyman,
    @OneToMany(mappedBy = "service", cascade = [CascadeType.REMOVE])
    @Column(name = "reservations")
    val reservations: MutableList<Reservation>,
)

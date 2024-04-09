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
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "customer_id")
    val id: String,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "strikes")
    val strikes: Int,
    @Column(name = "is_suspended")
    val isSuspended: Boolean,
    @ManyToOne
    @JoinColumn(name = "homeOrWorkshop_id")
    val homeOrWorkshop: HomeOrWorkshop,
    @OneToMany(mappedBy = "customer", cascade = [CascadeType.REMOVE])
    @Column(name = "notifications")
    val notifications: List<Notification>,
)

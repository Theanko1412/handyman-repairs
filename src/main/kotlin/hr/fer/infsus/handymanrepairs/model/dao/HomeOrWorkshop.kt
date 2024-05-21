package hr.fer.infsus.handymanrepairs.model.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "home_or_workshop")
data class HomeOrWorkshop(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "homeOrWorkshop_id")
    var id: String? = null,
    @Column(name = "name")
    val name: String,
    @ManyToOne
    @JoinColumn(name = "street_id", nullable = false)
    val street: Street,
)

package hr.fer.infsus.handymanrepairs.model.dao

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
@Table(name = "street")
data class Street(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "street_id")
    val id: String,
    @Column(name = "name")
    val name: String,
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    val city: City,
    @OneToMany(mappedBy = "street")
    @Column(name = "homeOrWorkshops")
    val homeOrWorkshops: List<HomeOrWorkshop>,
)

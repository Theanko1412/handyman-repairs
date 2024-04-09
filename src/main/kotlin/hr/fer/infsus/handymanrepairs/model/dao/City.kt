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
@Table(name = "city")
data class City(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "town_id")
    val id: String,
    @Column(name = "name")
    val name: String,
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    val country: Country,
    @OneToMany(mappedBy = "city", cascade = [CascadeType.REMOVE])
    val streets: List<Street>
)

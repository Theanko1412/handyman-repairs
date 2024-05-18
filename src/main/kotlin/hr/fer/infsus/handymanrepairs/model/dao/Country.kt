package hr.fer.infsus.handymanrepairs.model.dao

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "country")
data class Country(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "country_id")
    var id: String? = null,
    @Column(name = "name")
    val name: String,
    @OneToMany(mappedBy = "country", cascade = [CascadeType.REMOVE])
    @Column(name = "cities")
    val cities: List<City>,
)

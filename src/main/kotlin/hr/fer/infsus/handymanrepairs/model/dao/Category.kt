package hr.fer.infsus.handymanrepairs.model.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "category_id")
    val id: String,
    @Column(name = "name")
    val name: String,
    @Column(name = "description")
    val description: String,
    @OneToMany(mappedBy = "category")
    @Column(name = "services")
    val services: List<Service>,
)

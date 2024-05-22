package hr.fer.infsus.handymanrepairs.model.dao

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "handyman")
data class Handyman(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "handyman_id")
    var id: String? = null,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "password")
    var handymanPassword: String? = null,
    @Column(name = "type")
    val type: CustomerType = CustomerType.HANDYMAN,
    @Column(name = "rating")
    val rating: Double,
    @Column(name = "is_suspended")
    val isSuspended: Boolean,
    @ManyToOne
    @JoinColumn(name = "homeOrWorkshop_id")
    val homeOrWorkshop: HomeOrWorkshop? = null,
    @OneToMany(mappedBy = "handyman", cascade = [CascadeType.REMOVE])
    @Column(name = "notifications")
    val notifications: List<Notification>,
    @OneToMany(mappedBy = "handyman", cascade = [CascadeType.REMOVE])
    val services: List<Service>,
    @OneToOne(mappedBy = "handyman", cascade = [CascadeType.REMOVE])
    var schedule: Schedule? = null,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_HANDYMAN"))
    }

    override fun getPassword(): String {
        return handymanPassword!!
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return !isSuspended
    }

    override fun isAccountNonLocked(): Boolean {
        return !isSuspended
    }

    override fun isCredentialsNonExpired(): Boolean {
        return !isSuspended
    }

    override fun isEnabled(): Boolean {
        return !isSuspended
    }
}

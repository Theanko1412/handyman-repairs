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
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "customer_id")
    var id: String? = null,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "password")
    var customerPassword: String? = null,
    @Column(name = "type")
    val type: CustomerType = CustomerType.CUSTOMER,
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
    @OneToMany(mappedBy = "customer", cascade = [CascadeType.REMOVE])
    @Column(name = "reservations")
    val reservations: List<Reservation>,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_CUSTOMER"))
    }

    override fun getPassword(): String {
        return customerPassword!!
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return !isSuspended
    }

    override fun isAccountNonLocked(): Boolean {
        return strikes < 5
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return !isSuspended
    }

    override fun toString(): String {
        return "Customer(id=$id, firstName='$firstName', lastName='$lastName', email='$email', " +
            "strikes=$strikes, isSuspended=$isSuspended, homeOrWorkshop=${homeOrWorkshop.street})"
    }
}

enum class CustomerType {
    CUSTOMER,
    HANDYMAN,
}

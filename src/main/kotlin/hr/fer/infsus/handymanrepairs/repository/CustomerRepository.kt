package hr.fer.infsus.handymanrepairs.repository

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import org.springframework.data.jpa.repository.JpaRepository

//    @Column(name = "is_suspended")
//    val isSuspended: Boolean,
interface CustomerRepository : JpaRepository<Customer, String> {
    fun findCustomerById(id: String): Customer?
    fun findCustomerByEmail(email: String): Customer?
    fun findCustomersByFirstName(firstName: String): List<Customer>
    fun findCustomersByLastName(lastName: String): List<Customer>
    fun findCustomersByIsSuspended(isSuspended: Boolean): List<Customer>

    fun deleteCustomerById(id: String)
    fun deleteCustomerByEmail(email: String)
}
package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.Customer

interface ICustomerService {
    fun getCustomerById(id: String): Customer?

    fun getAllCustomers(): List<Customer>

    fun addCustomer(customer: Customer): Customer

    fun getCustomerByEmail(email: String): Customer?
}

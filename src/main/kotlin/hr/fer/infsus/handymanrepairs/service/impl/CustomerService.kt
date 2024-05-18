package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.repository.CustomerRepository
import hr.fer.infsus.handymanrepairs.service.ICustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService(
    @Autowired
    private val customerRepository: CustomerRepository,
) : ICustomerService {
    override fun getCustomerById(id: String): Customer? {
        return customerRepository.findCustomerById(id)
    }

    override fun getAllCustomers(): List<Customer> {
        return customerRepository.findAll()
    }
}

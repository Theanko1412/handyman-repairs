package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.CustomerDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.impl.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController(
    @Autowired
    private val customerService: CustomerService,
) {
    @GetMapping
    fun getAllCustomers(): List<CustomerDTO> {
        return customerService.getAllCustomers().map { it.toDTO() }
    }

    @GetMapping("/{id}")
    fun getCustomerById(
        @PathVariable id: String,
    ): CustomerDTO? {
        if (id.contains("@")) {
            return customerService.getCustomerByEmail(id)?.toDTO()
        }
        return customerService.getCustomerById(id)?.toDTO() ?: throw IllegalArgumentException("Customer with id $id does not exist")
    }
}

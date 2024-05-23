package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.CustomerDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.impl.CustomerService
import jakarta.persistence.EntityNotFoundException
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
    ): CustomerDTO {
        if (id.contains("@")) {
            return customerService.getCustomerByEmail(id)?.toDTO()
                ?: throw EntityNotFoundException("Customer with email $id does not exist")
        }
        return customerService.getCustomerById(id)?.toDTO()
            ?: throw EntityNotFoundException("Customer with id $id does not exist")
    }
}

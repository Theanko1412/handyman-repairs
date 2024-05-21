package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.repository.CustomerRepository
import hr.fer.infsus.handymanrepairs.repository.HandymanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    @Autowired
    private val customerRepository: CustomerRepository,
    @Autowired
    private val handymanRepository: HandymanRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val customer = customerRepository.findCustomerByEmail(username)
        if (customer != null) {
            return customer
        }

        val handyman = handymanRepository.findHandymanByEmail(username)
        if (handyman != null) {
            return handyman
        }

        throw UsernameNotFoundException("User not found with email: $username")
    }
}

class SpotlessDontComplain {}

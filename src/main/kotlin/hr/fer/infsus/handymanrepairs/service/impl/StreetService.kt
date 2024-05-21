package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Street
import hr.fer.infsus.handymanrepairs.repository.StreetRepository
import hr.fer.infsus.handymanrepairs.service.IStreetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StreetService(
    @Autowired
    private val streetRepository: StreetRepository,
) : IStreetService {
    override fun getAllStreets(): List<Street> {
        return streetRepository.findAll()
    }

    override fun getStreetById(id: String): Street? {
        return streetRepository.findStreetById(id)
    }
}

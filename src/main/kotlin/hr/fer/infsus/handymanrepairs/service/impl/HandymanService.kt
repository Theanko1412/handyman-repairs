package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.repository.HandymanRepository
import hr.fer.infsus.handymanrepairs.service.IHandymanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HandymanService(
    @Autowired
    private val handymanRepository: HandymanRepository,
) : IHandymanService {
    override fun getHandymanById(id: String): Handyman? {
        return handymanRepository.findHandymanById(id)
    }

    override fun getAllHandymen(): List<Handyman> {
        return handymanRepository.findAll()
    }
}

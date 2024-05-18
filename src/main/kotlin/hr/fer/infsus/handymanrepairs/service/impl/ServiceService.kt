package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Service
import hr.fer.infsus.handymanrepairs.model.dto.ServiceDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.repository.ServiceRepository
import hr.fer.infsus.handymanrepairs.service.ICategoryService
import hr.fer.infsus.handymanrepairs.service.IHandymanService
import hr.fer.infsus.handymanrepairs.service.IServiceService
import org.springframework.beans.factory.annotation.Autowired

@org.springframework.stereotype.Service
class ServiceService(
    @Autowired
    private val serviceRepository: ServiceRepository,
    @Autowired
    private val categoryService: ICategoryService,
    @Autowired
    private val handymanService: IHandymanService,
) : IServiceService {
    override fun getAllServices(): List<Service> {
        return serviceRepository.findAll()
    }

    override fun getServiceById(id: String): Service? {
        return serviceRepository.findServiceById(id)
    }

    override fun addService(serviceDTO: ServiceDTO): Service {
        val category = categoryService.getCategoryById(serviceDTO.categoryId)
        val handyman = handymanService.getHandymanById(serviceDTO.handymanId)
        require(category != null) { "Category with id ${serviceDTO.categoryId} does not exist" }
        require(handyman != null) { "Handyman with id ${serviceDTO.handymanId} does not exist" }

        val serviceDAO =
            serviceDTO.toDAO(
                category = category,
                handyman = handyman,
                reservations = emptyList(),
            )

        return serviceRepository.save(serviceDAO)
    }

    override fun updateServiceById(
        id: String,
        patches: Map<String, Any>,
    ): Service {
        val oldService = serviceRepository.findServiceById(id)
        require(oldService != null) { "Service with id $id does not exist" }
        require(patches.isNotEmpty()) { "No fields to update" }
        patches.forEach { (key, value) ->
            when (key) {
                "name" -> oldService.name = value as String
                "description" -> oldService.description = value as String
                "price" -> oldService.price = value as Double
                "duration" -> oldService.duration = value as Int
                "categoryId" -> {
                    val category = categoryService.getCategoryById(value as String)
                    require(category != null) { "Category with id $value does not exist" }
                    oldService.category = category
                }
            }
        }
        return serviceRepository.save(oldService)
    }

    override fun getServicesByHandymanId(id: String): List<Service> {
        return serviceRepository.findServicesByHandymanId(id)
    }

    override fun getServicesByCategoryId(id: String): List<Service> {
        return serviceRepository.findServicesByCategoryId(id)
    }
}

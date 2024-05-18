package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.Service
import hr.fer.infsus.handymanrepairs.model.dto.ServiceDTO

interface IServiceService {
    fun getAllServices(): List<Service>

    fun getServiceById(id: String): Service?

    fun addService(service: ServiceDTO): Service

    fun updateServiceById(
        id: String,
        patches: Map<String, Any>,
    ): Service

    fun getServicesByHandymanId(id: String): List<Service>

    fun getServicesByCategoryId(id: String): List<Service>
}

package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.ServiceDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.IServiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/service", produces = ["application/json"])
class ServiceController(
    @Autowired
    private val serviceService: IServiceService,
) {
    @GetMapping
    fun getServices(
        @RequestParam(required = false) handymanId: String? = null,
        @RequestParam(required = false) categoryId: String? = null,
    ): List<ServiceDTO> {
        require(handymanId == null || categoryId == null) { "Only one filter can be applied" }
        if (handymanId != null) {
            return serviceService.getServicesByHandymanId(handymanId).map { it.toDTO() }
        }
        if (categoryId != null) {
            return serviceService.getServicesByCategoryId(categoryId).map { it.toDTO() }
        }
        return serviceService.getAllServices().map { it.toDTO() }
    }

    @GetMapping("/{id}")
    fun getServiceById(
        @PathVariable id: String,
    ): ServiceDTO? {
        return serviceService.getServiceById(id)?.toDTO()
    }

    @PostMapping
    fun addService(
        @RequestBody serviceDTO: ServiceDTO,
    ): ServiceDTO {
        return serviceService.addService(serviceDTO).toDTO()
    }

    @PatchMapping("/{id}")
    fun updateService(
        @PathVariable id: String,
        @RequestBody patches: Map<String, Any>,
    ): ServiceDTO {
        return serviceService.updateServiceById(id, patches).toDTO()
    }
}

package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.Street
import hr.fer.infsus.handymanrepairs.model.dto.StreetDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.IStreetService
import jakarta.persistence.EntityNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/street")
class StreetController(
    private val streetService: IStreetService,
) {
    @GetMapping
    fun getAllStreets(): List<StreetDTO> {
        return streetService.getAllStreets().map(Street::toDTO)
    }

    @GetMapping("/{id}")
    fun getStreetById(
        @PathVariable id: String,
    ): StreetDTO {
        return streetService.getStreetById(id)?.toDTO() ?: throw EntityNotFoundException("Street with id $id not found")
    }
}

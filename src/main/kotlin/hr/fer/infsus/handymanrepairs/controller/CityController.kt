package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.model.dto.CityDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.ICityService
import jakarta.persistence.EntityNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/city")
class CityController(
    private val cityService: ICityService,
) {
    @GetMapping
    fun getAllCountries(): List<CityDTO> {
        return cityService.getAllCities().map(City::toDTO)
    }

    @GetMapping("/{id}")
    fun getCountryById(
        @PathVariable id: String,
    ): CityDTO {
        return cityService.getCityById(id)?.toDTO() ?: throw EntityNotFoundException("City with id $id not found")
    }
}

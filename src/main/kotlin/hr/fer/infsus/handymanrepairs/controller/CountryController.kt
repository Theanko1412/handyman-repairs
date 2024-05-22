package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.model.dto.CountryDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.ICountryService
import jakarta.persistence.EntityNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/country")
class CountryController(
        private val countryService: ICountryService
) {

    @GetMapping
    fun getAllCountries(): List<CountryDTO> {
        return countryService.getAllCountries().map(Country::toDTO)
    }

    @GetMapping("/{id}")
    fun getCountryById(@PathVariable id: String): CountryDTO {
        return countryService.getCountryById(id)?.toDTO() ?: throw EntityNotFoundException("Country with id $id not found")
    }
}
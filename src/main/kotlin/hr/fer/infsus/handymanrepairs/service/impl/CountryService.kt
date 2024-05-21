package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Country
import hr.fer.infsus.handymanrepairs.repository.CountryRepository
import hr.fer.infsus.handymanrepairs.service.ICountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CountryService(
    @Autowired
    private val countryRepository: CountryRepository,
) : ICountryService {
    override fun getAllCountries(): List<Country> {
        return countryRepository.findAll()
    }

    override fun getCountryById(id: String): Country? {
        return countryRepository.findCountryById(id)
    }
}

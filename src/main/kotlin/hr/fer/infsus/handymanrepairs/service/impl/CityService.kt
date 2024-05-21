package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.City
import hr.fer.infsus.handymanrepairs.repository.CityRepository
import hr.fer.infsus.handymanrepairs.service.ICityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CityService(
    @Autowired
    private val cityRepository: CityRepository,
) : ICityService {
    override fun getAllCities(): List<City> {
        return cityRepository.findAll()
    }

    override fun getCityById(id: String): City? {
        return cityRepository.findCityById(id)
    }
}

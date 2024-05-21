package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.HomeOrWorkshop
import hr.fer.infsus.handymanrepairs.model.dto.EnrichedHomeOrWorkshopDTO
import hr.fer.infsus.handymanrepairs.repository.HomeOrWorkshopRepository
import hr.fer.infsus.handymanrepairs.service.ICityService
import hr.fer.infsus.handymanrepairs.service.ICountryService
import hr.fer.infsus.handymanrepairs.service.IHomeOrWorkshopService
import hr.fer.infsus.handymanrepairs.service.IStreetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HomeOrWorkshopService(
    @Autowired
    private val homeOrWorkshopRepository: HomeOrWorkshopRepository,
    @Autowired
    private val streetService: IStreetService,
    @Autowired
    private val cityService: ICityService,
    @Autowired
    private val countryService: ICountryService,
) : IHomeOrWorkshopService {
    override fun getAllHomeOrWorkshops(): List<EnrichedHomeOrWorkshopDTO> {
        return homeOrWorkshopRepository.findAll().map {
            EnrichedHomeOrWorkshopDTO(
                id = it.id!!,
                streetName = it.street.name,
                streetNumber = it.street.number,
                cityName = it.street.city.name,
                name = it.name,
                countryName = it.street.city.country.name,
            )
        }
    }

    override fun getHomeOrWorkshopById(id: String): HomeOrWorkshop? {
        return homeOrWorkshopRepository.findHomeOrWorkshopById(id)
    }

    override fun getEnrichedHomeOrWorkshopById(id: String): EnrichedHomeOrWorkshopDTO {
        val homeOrWorkshop = homeOrWorkshopRepository.findHomeOrWorkshopById(id)
        require(homeOrWorkshop != null) { "Home or workshop with id $id not found" }
        val enriched =
            EnrichedHomeOrWorkshopDTO(
                id = id,
                streetName = homeOrWorkshop.street.name,
                streetNumber = homeOrWorkshop.street.number,
                cityName = homeOrWorkshop.street.city.name,
                name = homeOrWorkshop.name,
                countryName = homeOrWorkshop.street.city.country.name,
            )
        return enriched
    }
}

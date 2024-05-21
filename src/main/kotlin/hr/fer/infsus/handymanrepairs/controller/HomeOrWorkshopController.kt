package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.EnrichedHomeOrWorkshopDTO
import hr.fer.infsus.handymanrepairs.service.IHomeOrWorkshopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/home-or-workshop")
class HomeOrWorkshopController(
    @Autowired
    private val homeOrWorkshopService: IHomeOrWorkshopService,
) {
    @GetMapping
    fun getAllHomeOrWorkshops(): List<EnrichedHomeOrWorkshopDTO> {
        return homeOrWorkshopService.getAllHomeOrWorkshops()
    }

    @GetMapping("/{id}")
    fun getHomeOrWorkshopById(
        @PathVariable id: String,
    ): EnrichedHomeOrWorkshopDTO? {
        return homeOrWorkshopService.getEnrichedHomeOrWorkshopById(id)
    }
}

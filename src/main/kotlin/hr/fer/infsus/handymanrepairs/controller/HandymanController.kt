package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.HandymanDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.IHandymanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/handyman", produces = ["application/json"])
class HandymanController(
    @Autowired
    private val handymanService: IHandymanService,
) {
    @GetMapping
    fun getHandymen(): List<HandymanDTO>? {
        return handymanService.getAllHandymen().map { it.toDTO() }
    }

    @GetMapping("/{id}")
    fun getHandyman(
        @PathVariable id: String,
    ): HandymanDTO? {
        return handymanService.getHandymanById(id)?.toDTO()
    }
}

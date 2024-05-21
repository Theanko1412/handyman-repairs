package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.ScheduleDTO
import hr.fer.infsus.handymanrepairs.service.IScheduleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schedule")
class ScheduleController(
    @Autowired
    private val scheduleService: IScheduleService,
) {
    @GetMapping
    fun getAllSchedules(): List<ScheduleDTO> {
        return scheduleService.getAllScheduleDTOs()
    }

    @GetMapping("/{id}")
    fun getScheduleById(
        @PathVariable id: String,
    ): ScheduleDTO? {
        return scheduleService.getScheduleDTOById(id)
    }
}

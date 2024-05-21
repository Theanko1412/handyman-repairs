package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.model.dto.ScheduleDTO

interface IScheduleService {
    fun getAllSchedules(): List<Schedule>

    fun getScheduleById(id: String): Schedule?

    fun getAllScheduleDTOs(): List<ScheduleDTO>

    fun getScheduleDTOById(id: String): ScheduleDTO?
}

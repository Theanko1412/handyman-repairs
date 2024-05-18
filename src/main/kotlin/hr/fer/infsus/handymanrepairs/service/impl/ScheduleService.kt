package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.repository.ScheduleRepository
import hr.fer.infsus.handymanrepairs.service.IScheduleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScheduleService(
    @Autowired
    private val scheduleRepository: ScheduleRepository,
) : IScheduleService {
    override fun getAllSchedules(): List<Schedule> {
        return scheduleRepository.findAll()
    }

    override fun getScheduleById(id: String): Schedule? {
        return scheduleRepository.findScheduleById(id)
    }
}

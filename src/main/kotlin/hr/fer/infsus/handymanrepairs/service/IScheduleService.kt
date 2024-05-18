package hr.fer.infsus.handymanrepairs.service

import hr.fer.infsus.handymanrepairs.model.dao.Schedule

interface IScheduleService {
    fun getAllSchedules(): List<Schedule>

    fun getScheduleById(id: String): Schedule?
}

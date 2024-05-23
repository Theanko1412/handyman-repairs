package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Schedule
import hr.fer.infsus.handymanrepairs.model.dto.ScheduleDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ScheduleTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val dao = Schedule(
                "1",
                Handyman(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.HANDYMAN,
                    2.0,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                    null,
                ),
                emptyList(),
            )

            val dto = dao.toDTO()

            dto.id shouldBe "1"
            dto.handymanId shouldBe "1"
            dto.reservationIds shouldBe emptyList()
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val dto = ScheduleDTO(
                "1",
                "1",
                emptyList(),
            )

            val dao = dto.toDAO(
                Handyman(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.HANDYMAN,
                    2.0,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                    null,
                ),
                listOf()
            )

            dao.id shouldBe "1"
            dao.handyman!!.id shouldBe "1"
            dao.reservations shouldBe emptyList()
        }
    }
})
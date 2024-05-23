package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Service
import hr.fer.infsus.handymanrepairs.model.dto.ServiceDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ServiceTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val dao = Service(
                "1",
                "Service",
                "description",
                10,
                10,
                Category(
                    "1",
                    "Category",
                    "description",
                    emptyList(),
                ),
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
                mutableListOf(),
            )

            val dto = dao.toDTO()

            dto.id shouldBe dao.id
            dto.name shouldBe dao.name
            dto.description shouldBe dao.description
            dto.price shouldBe dao.price
            dto.duration shouldBe dao.duration
            dto.categoryId shouldBe dao.category.id
            dto.handymanId shouldBe dao.handyman.id
            dto.reservationIds shouldBe dao.reservations.map { it.id }
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val dto = ServiceDTO(
                "1",
                "Service",
                "description",
                10,
                10,
                "1",
                "1",
                mutableListOf(),
            )

            val dao = dto.toDAO(
                Category(
                    "1",
                    "Category",
                    "description",
                    emptyList(),
                ),
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

            dao.id shouldBe dto.id
            dao.name shouldBe dto.name
            dao.description shouldBe dto.description
            dao.price shouldBe dto.price
            dao.duration shouldBe dto.duration
            dao.category.id shouldBe dto.categoryId
        }
    }
})
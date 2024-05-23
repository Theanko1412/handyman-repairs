package hr.fer.infsus.handymanrepairs.model

import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dto.HandymanDTO
import hr.fer.infsus.handymanrepairs.model.dto.toDAO
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HandymanTest : FunSpec({

    context("dao to dto") {
        test("should convert dao to dto") {
            val handyman = Handyman(
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
            )

            val handymanDto = handyman.toDTO()

            handymanDto.id shouldBe "1"
            handymanDto.firstName shouldBe "John"
            handymanDto.lastName shouldBe "Doe"
            handymanDto.email shouldBe "email"
            handymanDto.password shouldBe "***"
            handymanDto.type shouldBe CustomerType.HANDYMAN.toString()
            handymanDto.rating shouldBe 2.0
            handymanDto.isSuspended shouldBe false
            handymanDto.homeOrWorkshopId shouldBe null
            handymanDto.notificationIds shouldBe emptyList()
            handymanDto.serviceIds shouldBe emptyList()
            handymanDto.scheduleId shouldBe null
        }
    }

    context("dto to dao") {
        test("should convert dto to dao") {
            val handymanDto = HandymanDTO(
                "1",
                "John",
                "Doe",
                "email",
                CustomerType.HANDYMAN.toString(),
                "password",
                2.0,
                false,
                null,
                emptyList(),
                emptyList(),
                null,
            )

            val handyman = handymanDto.toDAO()

            handyman.id shouldBe "1"
            handyman.firstName shouldBe "John"
            handyman.lastName shouldBe "Doe"
            handyman.email shouldBe "email"
            handyman.password shouldBe "password"
            handyman.type shouldBe CustomerType.HANDYMAN
            handyman.rating shouldBe 2.0
            handyman.isSuspended shouldBe false
            handyman.homeOrWorkshop shouldBe null
            handyman.notifications shouldBe emptyList()
            handyman.services shouldBe emptyList()
            handyman.schedule shouldBe null
        }
    }
})
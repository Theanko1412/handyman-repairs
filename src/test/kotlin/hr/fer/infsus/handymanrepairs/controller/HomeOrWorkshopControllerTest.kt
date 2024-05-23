package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dto.EnrichedHomeOrWorkshopDTO
import hr.fer.infsus.handymanrepairs.service.IHomeOrWorkshopService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class HomeOrWorkshopControllerTest : FunSpec({

    val serviceMock = mockk<IHomeOrWorkshopService>()
    val controller = HomeOrWorkshopController(serviceMock)

    val enrichedHomeOrWorkshops =
        listOf(
            EnrichedHomeOrWorkshopDTO(
                "1",
                "name",
                "streetName",
                1,
                "cityName",
                "countryName",
            ),
            EnrichedHomeOrWorkshopDTO(
                "2",
                "name",
                "streetName",
                1,
                "cityName",
                "countryName",
            ),
        )

    context("get") {
        test("get all") {
            every { serviceMock.getAllHomeOrWorkshops() } returns enrichedHomeOrWorkshops

            val result = controller.getAllHomeOrWorkshops()

            result shouldBe enrichedHomeOrWorkshops
        }

        test("get by id") {
            every { serviceMock.getEnrichedHomeOrWorkshopById("1") } returns enrichedHomeOrWorkshops[0]

            val result = controller.getHomeOrWorkshopById("1")

            result shouldBe enrichedHomeOrWorkshops[0]
        }
    }
})

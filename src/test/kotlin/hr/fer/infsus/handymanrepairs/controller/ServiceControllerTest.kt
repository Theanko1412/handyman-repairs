package hr.fer.infsus.handymanrepairs.controller

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Service
import hr.fer.infsus.handymanrepairs.model.dto.toDTO
import hr.fer.infsus.handymanrepairs.service.IServiceService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ServiceControllerTest : FunSpec({

    val serviceMock = mockk<IServiceService>()
    val controller = ServiceController(serviceMock)

    val serviceDaos =
        listOf(
            Service(
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
            ),
            Service(
                "2",
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
                    "2",
                    "Jane",
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
            ),
        )

    context("get") {
        test("get all") {
            every { serviceMock.getAllServices() } returns serviceDaos

            val result = controller.getServices()

            result shouldBe serviceDaos.map { it.toDTO() }
        }

        test("get by id") {
            every { serviceMock.getServiceById("1") } returns serviceDaos[0]

            val result = controller.getServiceById("1")

            result shouldBe serviceDaos[0].toDTO()
        }

        test("get by id throws exception when none found") {
            every { serviceMock.getServiceById("1") } returns null

            val exception =
                shouldThrow<Exception> {
                    controller.getServiceById("1")
                }

            exception.message shouldBe "Service with id 1 not found"
        }

        test("get all when both filters applied throws exception") {
            val exception =
                shouldThrow<Exception> {
                    controller.getServices("name", "category")
                }

            exception.message shouldBe "Only one filter can be applied"
        }

        test("get all by handymanId") {
            every { serviceMock.getServicesByHandymanId("1") } returns serviceDaos

            val result = controller.getServices(handymanId = "1")

            result shouldBe serviceDaos.map { it.toDTO() }
        }

        test("get all by categoryId") {
            every { serviceMock.getServicesByCategoryId("1") } returns serviceDaos

            val result = controller.getServices(categoryId = "1")

            result shouldBe serviceDaos.map { it.toDTO() }
        }
    }

    context("add") {
        test("add") {
            val serviceDto = serviceDaos[0].toDTO()
            every { serviceMock.addService(serviceDto) } returns serviceDaos[0]

            val result = controller.addService(serviceDto)

            result shouldBe serviceDaos[0].toDTO()
        }
    }

    context("update") {
        test("update") {
            val patches = mapOf("name" to "Service 2")
            every { serviceMock.updateServiceById("1", patches) } returns serviceDaos[0]

            val result = controller.updateService("1", patches)

            result shouldBe serviceDaos[0].toDTO()
        }
    }

    context("delete") {
        test("delete") {
            every { serviceMock.deleteServiceById("1") } returns serviceDaos[0]

            val result = controller.deleteService("1")

            result shouldBe serviceDaos[0].toDTO()
        }
    }
})

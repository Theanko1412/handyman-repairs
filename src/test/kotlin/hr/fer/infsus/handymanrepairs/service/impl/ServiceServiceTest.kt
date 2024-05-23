package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Category
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.model.dao.Handyman
import hr.fer.infsus.handymanrepairs.model.dao.Service
import hr.fer.infsus.handymanrepairs.model.dto.ServiceDTO
import hr.fer.infsus.handymanrepairs.repository.ServiceRepository
import hr.fer.infsus.handymanrepairs.service.ICategoryService
import hr.fer.infsus.handymanrepairs.service.IHandymanService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ServiceServiceTest : FunSpec({

    val serviceRepository = mockk<ServiceRepository>()
    val categoryService = mockk<ICategoryService>()
    val handymanService = mockk<IHandymanService>()

    val serviceService =
        ServiceService(
            serviceRepository = serviceRepository,
            categoryService = categoryService,
            handymanService = handymanService,
        )

    val dummyHandyman1 =
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
        )

    val dummyService1 =
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
            dummyHandyman1,
            mutableListOf(),
        )

    context("getServices") {
        test("should return all services") {
            val expectedServices =
                listOf(
                    dummyService1,
                    dummyService1.copy(id = "2"),
                    dummyService1.copy(id = "3"),
                )

            every { serviceRepository.findAll() } returns expectedServices

            val services = serviceService.getAllServices()

            services shouldBe expectedServices
        }

        test("should return service by id") {
            val expectedService = dummyService1

            every { serviceRepository.findServiceById("1") } returns expectedService

            val service = serviceService.getServiceById("1")

            service shouldBe expectedService
        }

        test("should return services by handymanId") {
            val expectedServices =
                listOf(
                    dummyService1,
                    dummyService1.copy(id = "2"),
                    dummyService1.copy(id = "3"),
                )

            every { serviceRepository.findServicesByHandymanId("1") } returns expectedServices

            val services = serviceService.getServicesByHandymanId("1")

            services shouldBe expectedServices
        }

        test("should return services by categoryId") {
            val expectedServices =
                listOf(
                    dummyService1,
                    dummyService1.copy(id = "2"),
                    dummyService1.copy(id = "3"),
                )

            every { serviceRepository.findServicesByCategoryId("1") } returns expectedServices

            val services = serviceService.getServicesByCategoryId("1")

            services shouldBe expectedServices
        }
    }

    context("addService") {
        test("should add service") {
            val serviceDTO =
                ServiceDTO(
                    "Service",
                    "name",
                    "description",
                    10,
                    10,
                    "1",
                    "1",
                )

            every { categoryService.getCategoryById("1") } returns
                Category(
                    "1",
                    "Category",
                    "description",
                    emptyList(),
                )
            every { handymanService.getHandymanById("1") } returns dummyHandyman1
            every { serviceRepository.save(any()) } returns dummyService1

            val service = serviceService.addService(serviceDTO)

            service shouldBe dummyService1
        }

        test("shuold throw exception if category does not exist") {
            val serviceDTO =
                ServiceDTO(
                    "Service",
                    "name",
                    "description",
                    10,
                    10,
                    "1",
                    "1",
                )

            every { categoryService.getCategoryById("1") } returns null

            val exception =
                shouldThrow<IllegalArgumentException> {
                    serviceService.addService(serviceDTO)
                }

            exception.message shouldBe "Category with id 1 does not exist"
        }

        test("shuold throw exception if handyman does not exist") {
            val serviceDTO =
                ServiceDTO(
                    "Service",
                    "name",
                    "description",
                    10,
                    10,
                    "1",
                    "1",
                )

            every { categoryService.getCategoryById("1") } returns
                Category(
                    "1",
                    "Category",
                    "description",
                    emptyList(),
                )
            every { handymanService.getHandymanById("1") } returns null

            val exception =
                shouldThrow<IllegalArgumentException> {
                    serviceService.addService(serviceDTO)
                }

            exception.message shouldBe "Handyman with id 1 does not exist"
        }
    }

    context("updateService") {
        test("should update service by id") {
            val patches =
                mapOf(
                    "name" to "Service",
                    "description" to "description",
                    "price" to 10,
                    "duration" to 10,
                    "categoryId" to "1",
                )

            every { serviceRepository.findServiceById("1") } returns dummyService1
            every { categoryService.getCategoryById("1") } returns
                Category(
                    "1",
                    "Category",
                    "description",
                    emptyList(),
                )
            every { serviceRepository.save(any()) } returns dummyService1

            val service = serviceService.updateServiceById("1", patches)

            service shouldBe dummyService1
        }

        test("shuold throw exception if service does not exist") {
            val patches =
                mapOf(
                    "name" to "Service",
                    "description" to "description",
                    "price" to 10,
                    "duration" to 10,
                    "categoryId" to "1",
                )

            every { serviceRepository.findServiceById("1") } returns null

            val exception =
                shouldThrow<IllegalArgumentException> {
                    serviceService.updateServiceById("1", patches)
                }

            exception.message shouldBe "Service with id 1 does not exist"
        }

        test("should throw exception if map is empty") {
            val patches = emptyMap<String, Any>()

            every { serviceRepository.findServiceById("1") } returns dummyService1

            val exception =
                shouldThrow<IllegalArgumentException> {
                    serviceService.updateServiceById("1", patches)
                }

            exception.message shouldBe "No fields to update"
        }

        test("should throw exception if category id in map doesnt exist") {
            val patches =
                mapOf(
                    "name" to "Service",
                    "description" to "description",
                    "price" to 10,
                    "duration" to 10,
                    "categoryId" to "1",
                )

            every { serviceRepository.findServiceById("1") } returns dummyService1
            every { categoryService.getCategoryById("1") } returns null

            val exception =
                shouldThrow<IllegalArgumentException> {
                    serviceService.updateServiceById("1", patches)
                }

            exception.message shouldBe "Category with id 1 does not exist"
        }
    }

    context("deleteService") {
        test("should delete service by id") {
            every { serviceRepository.findServiceById("1") } returns dummyService1

            every { serviceRepository.delete(dummyService1) } returns Unit

            val deletedService = serviceService.deleteServiceById("1")

            deletedService shouldBe dummyService1
        }

        test("should throw exception if service does not exist") {
            every { serviceRepository.findServiceById("1") } returns null

            val exception =
                shouldThrow<IllegalArgumentException> {
                    serviceService.deleteServiceById("1")
                }

            exception.message shouldBe "Service with id 1 does not exist"
        }
    }
})

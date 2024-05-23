package hr.fer.infsus.handymanrepairs.service.impl

import hr.fer.infsus.handymanrepairs.model.dao.Customer
import hr.fer.infsus.handymanrepairs.model.dao.CustomerType
import hr.fer.infsus.handymanrepairs.repository.CustomerRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CustomerServiceTest : FunSpec({

    val repositoryMock = mockk<CustomerRepository>()
    val service = CustomerService(repositoryMock)

    context("getCustomers") {
        test("should return all customers") {
            val expectedCustomers =
                listOf(
                    Customer(
                        "1",
                        "John",
                        "Doe",
                        "email",
                        "password",
                        CustomerType.CUSTOMER,
                        1,
                        false,
                        null,
                        emptyList(),
                        emptyList(),
                    ),
                    Customer(
                        "2",
                        "Jane",
                        "Doe",
                        "email",
                        "password",
                        CustomerType.CUSTOMER,
                        1,
                        false,
                        null,
                        emptyList(),
                        emptyList(),
                    ),
                )

            every { repositoryMock.findAll() } returns expectedCustomers

            val customers = service.getAllCustomers()

            customers shouldBe expectedCustomers
        }

        test("should return customer by id") {
            val expectedCustomer =
                Customer(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.CUSTOMER,
                    1,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                )

            every { repositoryMock.findCustomerById("1") } returns expectedCustomer

            val customer = service.getCustomerById("1")

            customer shouldBe expectedCustomer
        }

        test("should return customer by email") {
            val expectedCustomer =
                Customer(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.CUSTOMER,
                    1,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                )

            every { repositoryMock.findCustomerByEmail("email") } returns expectedCustomer

            val customer = service.getCustomerByEmail("email")

            customer shouldBe expectedCustomer
        }
    }

    context("addCustomer") {
        test("should add customer") {
            val customer =
                Customer(
                    "1",
                    "John",
                    "Doe",
                    "email",
                    "password",
                    CustomerType.CUSTOMER,
                    1,
                    false,
                    null,
                    emptyList(),
                    emptyList(),
                )

            every { repositoryMock.save(customer) } returns customer

            val addedCustomer = service.addCustomer(customer)

            addedCustomer shouldBe customer
        }
    }
})

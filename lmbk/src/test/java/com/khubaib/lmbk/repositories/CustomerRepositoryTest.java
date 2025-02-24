package com.khubaib.lmbk.repositories;

import com.khubaib.lmbk.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void saveCustomerTest(){
        Customer customer = customerRepository.save(Customer.builder()
                .customerName("test")
                .customerEmail("test@test.com")
                .build()
        );
        assertThat(customer.getId()).isNotNull();
    }

}
package com.khubaib.lmbk.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.khubaib.lmbk.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl(){

        this.customerMap = new HashMap<>();

        Customer customer1 = 
        Customer.builder()
        .id(UUID.randomUUID())
        .customerName("Customer Name 1")
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();


        Customer customer2 = 
        Customer.builder()
        .id(UUID.randomUUID())
        .customerName("Customer Name 2")
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();


        Customer customer3 = 
        Customer.builder()
        .id(UUID.randomUUID())
        .customerName("Customer Name 3")
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    };

    @Override
    public Customer getCustomerById(UUID customerId) {
        log.debug("in getCustomerById");
        return customerMap.get(customerId);
    }

    @Override
    public List<Customer> getCustomers() {
        log.debug("in getCustomers()");
        return new ArrayList<>(customerMap.values());
    }

}

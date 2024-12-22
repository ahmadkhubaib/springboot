package com.khubaib.lmbk.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.khubaib.lmbk.dto.CustomerDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl(){

        this.customerMap = new HashMap<>();

        CustomerDTO customer1 = 
        CustomerDTO.builder()
        .id(UUID.randomUUID())
        .customerName("CustomerDTO Name 1")
        .version(1)
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();


        CustomerDTO customer2 = 
        CustomerDTO.builder()
        .id(UUID.randomUUID())
        .customerName("CustomerDTO Name 2")
        .version(2)
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();


        CustomerDTO customer3 = 
        CustomerDTO.builder()
        .id(UUID.randomUUID())
        .customerName("CustomerDTO Name 3")
        .version(3)
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    };

    @Override
    public CustomerDTO getCustomerById(UUID customerId) {
        log.debug("in getCustomerById");
        return customerMap.get(customerId);
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        log.debug("in getCustomers()");
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = 
        CustomerDTO.builder()
        .id(UUID.randomUUID())
        .customerName(customer.getCustomerName())
        .version(customer.getVersion())
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDTO customer) {
        CustomerDTO existingCustomer = customerMap.get(customerId);

        customer.setCustomerName(customer.getCustomerName());
        customer.setVersion(customer.getVersion());
        

        customerMap.put(existingCustomer.getId(), existingCustomer);
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        customerMap.remove(customerId);
    }

}

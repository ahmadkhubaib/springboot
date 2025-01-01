package com.khubaib.lmbk.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.khubaib.lmbk.dto.CustomerDTO;

public interface CustomerService {

    Optional<CustomerDTO> getCustomerById(UUID customerId);
    
    List<CustomerDTO> getCustomers();

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer);

    Boolean deleteCustomerById(UUID customerId);
}

package com.khubaib.lmbk.services;

import java.util.List;
import java.util.UUID;

import com.khubaib.lmbk.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO getCustomerById(UUID customerId);
    
    List<CustomerDTO> getCustomers();

    CustomerDTO saveCustomer(CustomerDTO customer);

    void updateCustomer(UUID customerId, CustomerDTO customer);

    void deleteCustomer(UUID customerId);
}

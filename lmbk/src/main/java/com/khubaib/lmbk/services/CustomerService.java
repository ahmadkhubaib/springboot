package com.khubaib.lmbk.services;

import java.util.List;
import java.util.UUID;

import com.khubaib.lmbk.model.Customer;

public interface CustomerService {

    Customer getCustomerById(UUID customerId);
    
    List<Customer> getCustomers();
}

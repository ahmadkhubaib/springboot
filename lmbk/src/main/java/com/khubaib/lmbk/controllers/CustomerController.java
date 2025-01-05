package com.khubaib.lmbk.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khubaib.lmbk.dto.CustomerDTO;
import com.khubaib.lmbk.services.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";


    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<HttpStatus.Series> deleteCustomer(@PathVariable("{customerId}") UUID customerId) throws Exception {

        if(!customerService.deleteCustomerById(customerId)){
            throw new Exception("Customer not found");
        }

        return new ResponseEntity<HttpStatus.Series>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<HttpStatus.Series> updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customer) throws Exception{

        if(customerService.updateCustomerById(customerId, customer).isEmpty()){
            throw new Exception("Customer not found");
        }

        return new ResponseEntity<HttpStatus.Series>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<HttpStatus.Series> handlePost(@Validated @RequestBody CustomerDTO customer) {

        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", CUSTOMER_PATH + "/" + savedCustomer.getId().toString());

        return new ResponseEntity<HttpStatus.Series>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId).orElseThrow();
    }

    @RequestMapping(CUSTOMER_PATH)
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }
}

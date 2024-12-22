package com.khubaib.lmbk.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.khubaib.lmbk.dto.CustomerDTO;
import com.khubaib.lmbk.services.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @DeleteMapping("{customerId}")
    public ResponseEntity<HttpStatus.Series> deleteCustomer(@PathVariable("{customerId}") UUID customerId) {

        customerService.deleteCustomer(customerId);

        return new ResponseEntity<HttpStatus.Series>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<HttpStatus.Series> updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customer){

        customerService.updateCustomer(customerId, customer);

        return new ResponseEntity<HttpStatus.Series>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus.Series> handlePost(@RequestBody CustomerDTO customer) {

        CustomerDTO savedCustomer = customerService.saveCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/api/v1/customers/" + savedCustomer.getId());

        return new ResponseEntity<HttpStatus.Series>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{customerId}")
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }
}

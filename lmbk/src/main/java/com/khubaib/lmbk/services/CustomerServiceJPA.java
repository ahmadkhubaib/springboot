package com.khubaib.lmbk.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.khubaib.lmbk.dto.CustomerDTO;
import com.khubaib.lmbk.mappers.CustomerMapper;
import com.khubaib.lmbk.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Primary
public class CustomerServiceJPA implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID customerId) {
       return
            Optional
            .ofNullable(
              customerMapper
              .customerToCustomerDto(
                customerRepository.findById(customerId)
                .orElse(null)
              )
            );
    }
    @Override
    public List<CustomerDTO> getCustomers() {
        return
            customerRepository
            .findAll()
            .stream()
            .map(customerMapper::customerToCustomerDto)
            .collect(Collectors.toList());

    }
    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return
            customerMapper
            .customerToCustomerDto(
                customerRepository.save(
                    customerMapper
                    .customerDtoToCustomer(customer)
                )
            );
    }
    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepository
        .findById(customerId)
        .ifPresentOrElse(foundCustomer -> {
            foundCustomer.setCustomerName(customer.getCustomerName());
            atomicReference.set(
                Optional.of(
                    customerMapper.customerToCustomerDto(
                        customerRepository.save(foundCustomer)
                    )
                )
            );
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }
    @Override
    public Boolean deleteCustomerById(UUID customerId) {
        if(customerRepository.existsById(customerId)){
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

}

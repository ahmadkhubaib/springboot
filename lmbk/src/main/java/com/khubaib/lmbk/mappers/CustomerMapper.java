package com.khubaib.lmbk.mappers;

import org.mapstruct.Mapper;

import com.khubaib.lmbk.dto.CustomerDTO;
import com.khubaib.lmbk.entities.Customer;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO cusomterDto);

    CustomerDTO customerToCustomerDto(Customer customer);
}

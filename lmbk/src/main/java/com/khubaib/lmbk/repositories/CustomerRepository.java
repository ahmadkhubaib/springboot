package com.khubaib.lmbk.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khubaib.lmbk.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{

}

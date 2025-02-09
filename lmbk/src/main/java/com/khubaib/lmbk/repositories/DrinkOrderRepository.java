package com.khubaib.lmbk.repositories;

import com.khubaib.lmbk.entities.DrinkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrinkOrderRepository extends JpaRepository<DrinkOrder, UUID> {
}

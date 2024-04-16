package com.example.homework_16.repository;


import com.example.homework_16.model.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    List<Pizza> findAllByOrderById();
    List<Pizza> findAllByNameIgnoreCaseContainingOrderById(String name);
}

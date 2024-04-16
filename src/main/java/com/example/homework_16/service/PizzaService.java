package com.example.homework_16.service;

import com.example.homework_16.model.entity.Pizza;
import com.example.homework_16.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PizzaService {
    private PizzaRepository pizzaRepository;

    public Pizza getById(Integer id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public List<Pizza> getAll() {
        return pizzaRepository.findAllByOrderById();
    }

    public List<Pizza> getAllByName(String name) {
        return pizzaRepository.findAllByNameIgnoreCaseContainingOrderById(name);
    }

    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(pizza.getId());
        if (pizzaOptional.isPresent()) {
            Pizza pizzaToSave = pizzaOptional.get();
            pizzaToSave.setName(pizza.getName());
            pizzaToSave.setComposition(pizza.getComposition());
            return pizzaRepository.save(pizzaToSave);
        }
        return null;
    }

    public void delete(Integer id) {
        pizzaRepository.deleteById(id);
    }
}

package com.example.homework_16.controller;

import com.example.homework_16.model.entity.Pizza;
import com.example.homework_16.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/pizza")
public class PizzaController {
    private PizzaService pizzaService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('READ')")
    public String all(Model model) {
        model.addAttribute("pizzas", pizzaService.getAll());
        return "pages/pizza";
    }

    @PostMapping("/search")
    @PreAuthorize("hasAuthority('READ')")
    public String search(@RequestParam String name, Model model) {
        model.addAttribute("pizzas", pizzaService.getAllByName(name));
        return "pages/pizza";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('WRITE')")
    public String save(@RequestParam(required = false) Integer id,
                       @RequestParam String name,
                       @RequestParam String composition) {
        if (id == null) {
            pizzaService.save(Pizza.builder()
                    .name(name)
                    .composition(composition)
                    .build());
        } else {
            pizzaService.update(Pizza.builder()
                    .id(id)
                    .name(name)
                    .composition(composition)
                    .build());
        }
        return "redirect:/pizza/all";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public String delete(@PathVariable Integer id) {
        pizzaService.delete(id);
        return "redirect:/pizza/all";
    }
}

package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.IngredientService;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

  @Autowired
  private PizzaService pizzaService;

  @Autowired
  private IngredientService ingredientService;

  // INDEX
  @GetMapping
  public String index(Model model) {
    model.addAttribute("pizzas", pizzaService.findAll());
    return "pizzas/index";
  }

  // FILTERED INDEX
  @GetMapping("/search")
  public String searchByName(@RequestParam(name = "name") String name, Model model) {
    model.addAttribute("pizzas", pizzaService.findByName(name));
    return "pizzas/index";
  }

  // SHOW
  @GetMapping("/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("pizza", pizzaService.getById(id));
    return "pizzas/show";
  }

  // CREATE
  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("pizza", new Pizza());
    model.addAttribute("ingredients", ingredientService.findAll());
    return "/pizzas/create-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredients", ingredientService.findAll());
      return "/pizzas/create-edit";
    }
    pizzaService.create(formPizza);
    return "redirect:/pizzas";
  }

  // UPDATE
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("pizza", pizzaService.getById(id));
    model.addAttribute("ingredients", ingredientService.findAll());
    model.addAttribute("edit", true);
    return "/pizzas/create-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredients", ingredientService.findAll());
      return "/pizzas/create-edit";
    }
    pizzaService.update(formPizza);
    return "redirect:/pizzas";
  }

  // DELETE
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable("id") Integer id) {
    pizzaService.deleteById(id);
    return "redirect:/pizzas";
  }

  @GetMapping("/{id}/offer")
  public String createOffer(@PathVariable Integer id, Model model) {
    model.addAttribute("offer", pizzaService.newOfferByPizzaId(id));
    return "special-offers/create-edit";
  }

}

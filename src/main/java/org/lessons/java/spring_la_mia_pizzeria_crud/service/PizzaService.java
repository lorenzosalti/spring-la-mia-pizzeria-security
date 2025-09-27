package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

  @Autowired
  private PizzaRepository pizzaRepository;

  public List<Pizza> findAll() {
    return pizzaRepository.findAll();
  }

  public List<Pizza> findByName(String name) {
    return pizzaRepository.findByNameContaining(name);
  }

  public Pizza getById(Integer id) {
    return pizzaRepository.findById(id).get();
  }

  public Pizza create(Pizza pizza) {
    return pizzaRepository.save(pizza);
  }

  public Pizza update(Pizza pizza) {
    return pizzaRepository.save(pizza);
  }

  public void deleteById(Integer id) {
    Pizza pizzaToDelete = getById(id);
    for (Ingredient ingredient : pizzaToDelete.getIngredients()) {
      List<Pizza> pizzas = ingredient.getPizzas();
      pizzas.remove(pizzaToDelete);
    }
    pizzaRepository.deleteById(id);
  }

}

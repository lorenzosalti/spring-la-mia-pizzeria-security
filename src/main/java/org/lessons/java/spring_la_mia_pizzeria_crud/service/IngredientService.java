package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

  @Autowired
  private IngredientRepository ingredientRepository;

  public List<Ingredient> findAll() {
    return ingredientRepository.findAll();
  }

  public Ingredient getById(Integer id) {
    return ingredientRepository.findById(id).get();
  }

  public Ingredient create(Ingredient ingredient) {
    return ingredientRepository.save(ingredient);
  }

  public Ingredient update(Ingredient ingredient) {
    return ingredientRepository.save(ingredient);
  }

  public void deleteById(Integer id) {
    Ingredient ingredientToDelete = getById(id);
    for (Pizza pizza : ingredientToDelete.getPizzas()) {
      List<Ingredient> Ingredients = pizza.getIngredients();
      Ingredients.remove(ingredientToDelete);
    }
    ingredientRepository.deleteById(id);
  }

}

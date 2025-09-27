package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

  @Autowired
  private IngredientService ingredientService;

  @GetMapping()
  public String index(Model model) {
    model.addAttribute("ingredients", ingredientService.findAll());
    return "ingredients/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {
    model.addAttribute("ingredient", ingredientService.getById(id));
    return "ingredients/show";
  }

  @GetMapping("/create")
  public String create(Model model) {
    Ingredient ingredient = new Ingredient();
    model.addAttribute("ingredient", ingredient);
    return "ingredients/create-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredientToStore, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "ingredients/create-edit";
    }
    ingredientService.create(ingredientToStore);
    return "redirect:/ingredients";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("ingredient", ingredientService.getById(id));
    model.addAttribute("edit", true);
    return "ingredients/create-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredientToUpdate, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "ingredients/create-edit";
    }
    ingredientService.update(ingredientToUpdate);
    return "redirect:/ingredients";
  }

  @PostMapping("delete/{id}")
  public String delete(@PathVariable Integer id) {
    ingredientService.deleteById(id);
    return "redirect:/ingredients";
  }
}

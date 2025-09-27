package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
// import org.lessons.java.spring_la_mia_pizzeria_crud.service.IngredientService;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {

  @Autowired
  private PizzaService pizzaService;

  // @Autowired
  // private IngredientService ingredientService;

  @GetMapping
  public ResponseEntity<List<Pizza>> index() {
    return new ResponseEntity<List<Pizza>>(pizzaService.findAll(), HttpStatus.OK);
  }
}

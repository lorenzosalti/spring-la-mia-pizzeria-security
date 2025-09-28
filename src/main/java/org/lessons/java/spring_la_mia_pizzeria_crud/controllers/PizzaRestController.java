package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
// import org.lessons.java.spring_la_mia_pizzeria_crud.service.IngredientService;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/search")
  public ResponseEntity<List<Pizza>> searchByName(@RequestParam(name = "name") String name) {
    return new ResponseEntity<List<Pizza>>(pizzaService.findByName(name),
        HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pizza> show(@PathVariable Integer id) {
    Optional<Pizza> pizzaAttempt = pizzaService.findById(id);
    if (pizzaAttempt.isEmpty()) {
      return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Pizza> store(@RequestBody Pizza pizzaToStore) {
    return new ResponseEntity<Pizza>(pizzaService.create(pizzaToStore), HttpStatus.OK);
  }
}

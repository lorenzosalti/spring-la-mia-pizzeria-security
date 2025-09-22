package org.lessons.java.spring_la_mia_pizzeria_crud.repositorie;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

  // CUSTOM QUERYS

  public List<Pizza> findByNameContaining(String name);
}

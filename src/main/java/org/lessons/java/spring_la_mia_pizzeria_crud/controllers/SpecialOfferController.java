package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repositories.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class SpecialOfferController {

  @Autowired
  private SpecialOfferRepository offerRepository;

  @PostMapping("/{id}/offer")
  public String storeOffer(@Valid @ModelAttribute("offer") SpecialOffer offerToStore, BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "special-offers/create";
    }

    return "redirect:/pizzas";
  }

}

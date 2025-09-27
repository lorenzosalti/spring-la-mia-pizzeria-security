package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/offers")
public class SpecialOfferController {

  @Autowired
  private SpecialOfferService offerService;

  @GetMapping("/{id}")
  public String showSpecialOffer(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("offer", offerService.getById(id));
    return "special-offers/show";
  }

  @PostMapping("/create")
  public String storeOffer(@Valid @ModelAttribute("offer") SpecialOffer offerToStore,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "special-offers/create-edit";
    }
    offerService.create(offerToStore);
    return "redirect:/pizzas";
  }

  @GetMapping("/edit/{id}")
  public String editSpecialOffer(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("offer", offerService.getById(id));
    model.addAttribute("edit", true);
    return "special-offers/create-edit";
  }

  @PostMapping("/edit/{id}")
  public String updateSpecialOffer(@Valid @ModelAttribute("offer") SpecialOffer offerToUpdate,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "special-offers/create-edit";
    }
    offerService.update(offerToUpdate);
    return "redirect:/pizzas";
  }

}

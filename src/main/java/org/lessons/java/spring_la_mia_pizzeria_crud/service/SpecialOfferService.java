package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repositories.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

  @Autowired
  private SpecialOfferRepository offerRepository;

  public List<SpecialOffer> findAll() {
    return offerRepository.findAll();
  }

  public SpecialOffer getById(Integer id) {
    return offerRepository.findById(id).get();
  }

  public SpecialOffer create(SpecialOffer specialOffer) {
    return offerRepository.save(specialOffer);
  }

  public SpecialOffer update(SpecialOffer specialOffer) {
    return offerRepository.save(specialOffer);
  }

}

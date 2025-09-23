package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offers")
public class SpecialOffer {

  // ATTRIBUTES
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "pizza_id", nullable = false)
  private Pizza pizza;

  @Column(name = "starting_date", nullable = false)
  @NotNull(message = "La data di inizio è necessaria")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate startingDate;

  @Column(name = "ending_date", nullable = false)
  @NotNull(message = "La data di fine è necessaria")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate endingDate;

  @Column(name = "title")
  @NotBlank(message = "L'offerta speciale deve avere un titolo")
  private String title;

  // CONSTRUCTOR
  public SpecialOffer() {
  }

  // GETTERS AND SETTERS
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Pizza getPizza() {
    return pizza;
  }

  public void setPizza(Pizza pizza) {
    this.pizza = pizza;
  }

  public LocalDate getStartingDate() {
    return startingDate;
  }

  public void setStartingDate(LocalDate startingDate) {
    this.startingDate = startingDate;
  }

  public LocalDate getEndingDate() {
    return endingDate;
  }

  public void setEndingDate(LocalDate endingDate) {
    this.endingDate = endingDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}

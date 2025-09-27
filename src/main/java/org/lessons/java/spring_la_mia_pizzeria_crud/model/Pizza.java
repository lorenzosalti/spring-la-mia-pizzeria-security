package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

  // ATTRIBUTES

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false)
  @NotBlank(message = "Il nome non può essere un campo vuoto")
  @Size(max = 40, message = "La lunghezza del nome non può essere maggiore di 40 caratteri")
  private String name;

  @Column(name = "description", nullable = false)
  @NotBlank(message = "La descrizione con gli ingredienti è obbligatoria")
  @Lob
  private String description;

  @Column(name = "picture")
  private String pictureUrl;

  @Column(name = "price", nullable = false)
  @NotNull(message = "Il prezzo non può essere un campo vuoto")
  @DecimalMin(value = "1.00", message = "Il prezzo non può essere minore di 1")
  @DecimalMax(value = "999.99", message = "Nemmeno Carlo Cracco farebbe pagare una pizza così tanto. Deve esserci un errore!")
  private BigDecimal price;

  @OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE)
  @JsonManagedReference
  private List<SpecialOffer> offers;

  @ManyToMany()
  @JsonBackReference
  @JoinTable(name = "ingredient_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private List<Ingredient> ingredients;

  // CONSTRUCTORS

  public Pizza() {
  }

  // GETTERS AND SETTERS

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPictureUrl() {
    return this.pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return String.format("%s  %s €", this.name, this.price);
  }

  public List<SpecialOffer> getOffers() {
    return offers;
  }

  public void setOffers(List<SpecialOffer> offers) {
    this.offers = offers;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

}

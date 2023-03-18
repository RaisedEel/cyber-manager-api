package com.raisedeel.cybermanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "computer")
public class Computer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NonNull
  @Column(name = "name", nullable = false)
  private String name;

  @NonNull
  @Column(name = "price", nullable = false)
  private int price;

  @NonNull
  @Column(name = "brand", nullable = false)
  private String brand;

  @NonNull
  @Column(name = "antiquity", nullable = false)
  private int antiquity;

  @NonNull
  @Column(name = "last_revision", nullable = false)
  private LocalDate revision;

  @NonNull
  @Column(name = "serial", nullable = false)
  private String serial;

  @NonNull
  @Column(name = "description", nullable = false)
  private String description;
}

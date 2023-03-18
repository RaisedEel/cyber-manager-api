package com.raisedeel.cybermanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "computer")
public class Computer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @NonNull
  @Column(name = "name")
  private String name;

  @NonNull
  @Column(name = "price")
  private int price;

  @NonNull
  @Column(name = "brand")
  private String brand;

  @NonNull
  @Column(name = "antiquity")
  private int antiquity;

  @NonNull
  @Column(name = "last_revision")
  private Date revision;

  @NonNull
  @Column(name = "serial")
  private String serial;

  @NonNull
  @Column(name = "description")
  private String description;
}

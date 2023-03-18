package com.raisedeel.cybermanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rental")
public class Rental {

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
  @Column(name = "start_time", nullable = false)
  private LocalTime startTime;

  @Column(name = "end_time")
  private LocalTime endTime;

  @Column(name = "rented_hours")
  private int rentedHours = 0;

  @Column(name = "total")
  private int total;
}

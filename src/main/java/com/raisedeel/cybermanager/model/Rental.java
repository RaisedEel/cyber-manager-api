package com.raisedeel.cybermanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;

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
  @Column(name = "rent_date", nullable = false)
  private LocalDate rentDate;

  @NonNull
  @Column(name = "price", nullable = false)
  private int price;

  @NonNull
  @Column(name = "start_time", nullable = false)
  private Date startTime;

  @Column(name = "end_time")
  private Date endTime;
  
  @Column(name = "total")
  private int total;
}

package com.raisedeel.cybermanager.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {

  private LocalDateTime timestamp;
  private List<String> errorMessage;

  public ErrorResponse(List<String> errorMessage) {
    this.timestamp = LocalDateTime.now();
    this.errorMessage = errorMessage;
  }
}

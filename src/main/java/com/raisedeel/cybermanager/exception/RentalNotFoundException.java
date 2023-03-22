package com.raisedeel.cybermanager.exception;

public class RentalNotFoundException extends RuntimeException {

  public RentalNotFoundException(Long id) {
    super("Could not find the rental with the id of " + id);
  }

}

package com.raisedeel.cybermanager.exception;

public class ComputerNotFoundException extends RuntimeException {

  public ComputerNotFoundException(Long id) {
    super("Could not find the computer with the id of " + id);
  }
  
}

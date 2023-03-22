package com.raisedeel.cybermanager.exception;

import java.time.LocalDateTime;

public class EndTimeBeforeStartTimeException extends RuntimeException {

  public EndTimeBeforeStartTimeException(LocalDateTime start, LocalDateTime end) {
    super("Time of the end of the rent (" + end + ") cannot be before the start (" + start + ")");
  }

}

package com.justin.unittest.exception;

/**
 * description: custom exception of duplicate user.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 12:03 PM
 */
public class DuplicateUserException extends RuntimeException {
  /**
   * description: constructor with message parameter.
   *
   * @param message message of exception.
   */
  public DuplicateUserException(String message) {
    super(message);
  }
}

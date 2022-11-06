package com.justin.unittest.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: persistent object of user.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 2:10 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  /**
   * user's id.
   */
  private Long id;

  /**
   * user's name.
   */
  private String name;

  /**
   * user's age.
   */
  private Integer age;

  /**
   * user's phone number.
   */
  private String phoneNumber;
}

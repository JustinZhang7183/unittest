package com.justin.unittest.service;

import com.justin.unittest.exception.DuplicateUserException;
import com.justin.unittest.po.User;
import org.springframework.stereotype.Service;

/**
 * description: service layer of user.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 2:13 PM
 */
@Service
public class UserService {
  /**
   * description: insert a data item of user.
   *
   * @param user instance of a concrete user
   */
  public void insert(User user) {
    throw new DuplicateUserException("exist the same user");
  }
}

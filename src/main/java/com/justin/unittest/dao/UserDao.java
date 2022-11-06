package com.justin.unittest.dao;

import com.justin.unittest.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * description: operation of persistent layer.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 11:48 AM
 */
@Repository
public interface UserDao {
  /**
   * description: initialize table of user.
   */
  void initializeUserTable();

  /**
   * description: truncate table of user.
   */
  void truncateUserTable();

  /**
   * description: query user by name of user.
   *
   * @param name name of user
   * @return User.class
   */
  User queryByName(String name);

  /**
   * description: insert a data item of user.
   *
   * @param user an instance of concrete user
   */
  void insert(@Param("user") User user);
}

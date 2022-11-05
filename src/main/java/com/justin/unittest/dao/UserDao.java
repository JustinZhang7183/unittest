package com.justin.unittest.dao;

import com.justin.unittest.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    void initializeUserTable();

    void truncateUserTable();

    User queryByName(String name);

    void insert(@Param("user") User user);
}

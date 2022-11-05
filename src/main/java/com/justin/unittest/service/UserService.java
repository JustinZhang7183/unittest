package com.justin.unittest.service;

import com.justin.unittest.exception.DuplicateUserException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void insert() {
        throw new DuplicateUserException("exist the same user");
    }
}

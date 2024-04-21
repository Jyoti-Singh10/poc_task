package com.springjpa.poctask.service;

import com.springjpa.poctask.dto.UserCreateRequest;
import com.springjpa.poctask.entities.Users;

import java.util.List;

public interface UserService {

    Users createUser(UserCreateRequest user);

    List<Users> getUsers();

    Users getUser(int id);

    Users putUser(int id, Users user);

    void deleteUser(int id);
}

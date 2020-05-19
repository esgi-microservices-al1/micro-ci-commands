package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.User;

import java.util.List;

public interface IUserService {
    User addUsers(User User);
    List<User> getUsers();
    User getUsersById(Long id);
}

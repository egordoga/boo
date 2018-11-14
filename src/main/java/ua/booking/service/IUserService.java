package ua.booking.service;

import ua.booking.entity.User;

public interface IUserService {
    void saveUser(User user);

    User findUserByName(String name);
}

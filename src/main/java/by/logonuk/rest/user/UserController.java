package by.logonuk.rest.user;

import by.logonuk.domain.entity.User;
import by.logonuk.dto.user.UserCreateRequest;

import java.util.List;

public interface UserController {

    List<User> findAll();

    User createUser(UserCreateRequest request);
}

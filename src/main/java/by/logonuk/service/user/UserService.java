package by.logonuk.service.user;

import by.logonuk.domain.entity.User;
import by.logonuk.dto.user.UserCreateRequest;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User createUser(UserCreateRequest request);
}

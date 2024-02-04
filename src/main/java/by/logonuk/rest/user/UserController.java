package by.logonuk.rest.user;

import by.logonuk.domain.entity.User;
import by.logonuk.dto.user.UserCreateRequest;

public interface UserController {

    User createUser(UserCreateRequest request);
}

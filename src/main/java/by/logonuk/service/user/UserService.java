package by.logonuk.service.user;

import by.logonuk.domain.entity.User;
import by.logonuk.dto.user.UserCreateRequest;

public interface UserService {

    User createUser(UserCreateRequest request);
}

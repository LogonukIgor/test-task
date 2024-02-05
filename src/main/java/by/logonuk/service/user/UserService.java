package by.logonuk.service.user;

import by.logonuk.domain.entity.User;
import by.logonuk.dto.request.user.UserCreateRequest;
import by.logonuk.dto.request.user.UserWithAccountsRequest;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findOne(UserWithAccountsRequest request);

    User createUser(UserCreateRequest request);
}

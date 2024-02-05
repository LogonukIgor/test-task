package by.logonuk.rest.user;

import by.logonuk.domain.entity.User;
import by.logonuk.dto.request.user.UserCreateRequest;
import by.logonuk.dto.request.user.UserWithAccountsRequest;
import by.logonuk.dto.responce.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "User", description = "Упровление профилем пользователя")
public interface UserController {

    @Operation(summary = "Найти всех пользователей")
    List<User> findAll();

    @Operation(summary = "Найти пользователя и его счета")
    UserResponse findOne(UserWithAccountsRequest request);

    @Operation(summary = "Создать пользователя")
    User createUser(UserCreateRequest request);
}

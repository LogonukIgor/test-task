package by.logonuk.rest.user;

import by.logonuk.domain.entity.User;
import by.logonuk.dto.user.UserCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "User", description = "User API")
public interface UserController {

    @Operation(summary = "Найти всех пользователей")
    List<User> findAll();

    @Operation(summary = "Создать пользователя")
    User createUser(UserCreateRequest request);
}

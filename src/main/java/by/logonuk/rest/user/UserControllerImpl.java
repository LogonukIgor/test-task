package by.logonuk.rest.user;

import by.logonuk.domain.entity.User;
import by.logonuk.dto.user.UserCreateRequest;
import by.logonuk.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    private final UserService service;

    @Override
    @PostMapping("/create")
    public User createUser(@RequestBody UserCreateRequest request) {
        return service.createUser(request);
    }
}

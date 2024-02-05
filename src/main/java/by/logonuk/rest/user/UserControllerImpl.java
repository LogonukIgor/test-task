package by.logonuk.rest.user;

import by.logonuk.domain.entity.User;
import by.logonuk.domain.mapper.UserMapper;
import by.logonuk.dto.request.user.UserCreateRequest;
import by.logonuk.dto.request.user.UserWithAccountsRequest;
import by.logonuk.dto.responce.user.UserResponse;
import by.logonuk.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    private final UserService service;

    private final UserMapper mapper;

    @Override
    @GetMapping("/all")
    public List<User> findAll() {
        return service.findAll();
    }

    @Override
    @GetMapping("/info")
    public UserResponse findOne(@RequestBody @Valid UserWithAccountsRequest request) {
        User user = service.findOne(request);
        return mapper.mapUserToUserResponse(user);
    }

    @Override
    @PostMapping("/create")
    public User createUser(@RequestBody @Valid UserCreateRequest request) {
        return service.createUser(request);
    }
}

package by.logonuk.service.user;

import by.logonuk.domain.entity.User;
import by.logonuk.domain.exceptions.UserAlreadyExistsException;
import by.logonuk.domain.exceptions.UserNotFoundException;
import by.logonuk.domain.mapper.UserMapper;
import by.logonuk.dto.request.user.UserCreateRequest;
import by.logonuk.dto.request.user.UserWithAccountsRequest;
import by.logonuk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findOne(UserWithAccountsRequest request) {
        Optional<User> existingUser = repository.findByLogin(request.getLogin());
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(request.getLogin());
        }
        return existingUser.get();
    }

    @Override
    public User createUser(UserCreateRequest request) {
        Optional<User> existingUser = repository.findByLogin(request.getLogin());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(request.getLogin());
        }
        User user = mapper.mapUserFromRequest(request);

        return repository.save(user);
    }
}


package by.logonuk.service.user;

import by.logonuk.domain.entity.User;
import by.logonuk.domain.exceptions.UserAlreadyExistsException;
import by.logonuk.dto.user.UserCreateRequest;
import by.logonuk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    @Override
    public User createUser(UserCreateRequest request) {
        Optional<User> existingUser = repository.findByLogin(request.getLogin());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(request.getLogin());
        }
        User user = mapUser(request);

        return repository.save(user);
    }

    private User mapUser(UserCreateRequest request){
        User user = new User();

        user.setName(request.getName());
        user.setLogin(request.getLogin());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreationDate(timestamp);
        user.setModificationDate(timestamp);

        return user;
    }
}


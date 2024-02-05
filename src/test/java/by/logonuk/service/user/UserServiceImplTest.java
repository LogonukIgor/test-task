package by.logonuk.service.user;

import by.logonuk.domain.entity.User;
import by.logonuk.dto.request.user.UserCreateRequest;
import by.logonuk.dto.request.user.UserWithAccountsRequest;
import by.logonuk.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private static final String LOGIN = "testUser";
    private static final String NAME = "Test User";

    @Test
    public void testFindAll() {
        // Ожидаемый результат (пустой список)
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // Вызываем метод и проверяем результат
        List<User> users = userService.findAll();
        assertTrue(users.isEmpty());

        // Проверяем, что метод findAll был вызван ровно один раз
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindOne() {
        // Заглушка для метода findByLogin
        when(userRepository.findByLogin(eq(LOGIN))).thenReturn(Optional.of(createTestUser()));

        // Вызываем метод и проверяем результат
        UserWithAccountsRequest request = new UserWithAccountsRequest();
        request.setLogin(LOGIN);
        User user = userService.findOne(request);

        assertNotNull(user);
        assertEquals(LOGIN, user.getLogin());

        // Проверяем, что метод findByLogin был вызван ровно один раз
        verify(userRepository, times(1)).findByLogin(eq(LOGIN));
    }

    @Test
    public void testCreateUser() {
        // Заглушка для метода findByLogin
        when(userRepository.findByLogin(eq(LOGIN))).thenReturn(Optional.empty());

        // Заглушка для метода save
        when(userRepository.save(any(User.class))).thenReturn(createTestUser());

        // Создаем запрос
        UserCreateRequest request = new UserCreateRequest();
        request.setLogin(LOGIN);
        request.setName(NAME);

        // Вызываем метод и проверяем результат
        User user = userService.createUser(request);

        assertNotNull(user);
        assertEquals(LOGIN, user.getLogin());

        // Проверяем, что метод findByLogin был вызван ровно один раз
        verify(userRepository, times(1)).findByLogin(eq(LOGIN));

        // Проверяем, что метод save был вызван ровно один раз
        verify(userRepository, times(1)).save(any(User.class));
    }

    private User createTestUser() {
        User user = new User();
        user.setLogin(LOGIN);
        user.setName(NAME);
        user.setCreationDate(new Timestamp(System.currentTimeMillis()));
        user.setModificationDate(new Timestamp(System.currentTimeMillis()));
        return user;
    }
}

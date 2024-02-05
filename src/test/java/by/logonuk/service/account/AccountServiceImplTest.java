package by.logonuk.service.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.User;
import by.logonuk.domain.enums.Currency;
import by.logonuk.domain.enums.OperationType;
import by.logonuk.dto.request.account.AccountCreateRequest;
import by.logonuk.dto.request.account.AccountTransactionRequest;
import by.logonuk.dto.request.account.AccountTransferRequest;
import by.logonuk.dto.request.account.GetAccountInfoRequest;
import by.logonuk.repository.AccountRepository;
import by.logonuk.repository.UserRepository;
import by.logonuk.service.manager.ManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {
    @Mock
    private ManagerService managerService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private static final String ACCOUNT_NUMBER = "3110fb9c-e97b-4686-bdb6-2382af7193da";
    private static final UUID ACCOUNT_UUID = UUID.fromString("3110fb9c-e97b-4686-bdb6-2382af7193da");
    private static final String CODE = "1234";
    private static final String LOGIN = "testUser";
    private static final String CURRENCY = "USD";
    private static final Double AMOUNT = 100.0;

    @Test
    public void testGetInfo() {
        // Заглушка для метода findByUuid
        when(accountRepository.findByUuid(any(UUID.class))).thenReturn(Optional.of(createTestAccount()));

        // Вызываем метод и проверяем результат
        GetAccountInfoRequest request = new GetAccountInfoRequest();
        request.setAccountNumber(ACCOUNT_NUMBER);
        Account account = accountService.getInfo(request);

        assertNotNull(account);
        assertEquals(ACCOUNT_NUMBER, account.getUuid().toString());

        // Проверяем, что метод findByUuid был вызван ровно один раз
        verify(accountRepository, times(1)).findByUuid(any(UUID.class));
    }

    @Test
    public void testAccountCreate() {
        AccountCreateRequest request = new AccountCreateRequest();
        request.setCode(CODE);
        request.setCurrency(CURRENCY);
        request.setLogin(LOGIN);

        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.of(createTestUser()));

        Account account = accountService.accountCreate(request);

        assertNull(account);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testReplenishBalance() {
        // Заглушка для метода valid
        when(accountRepository.findByUuidAndCode(any(UUID.class), any(String.class))).thenReturn(Optional.of(createTestAccount()));

        // Заглушка для метода save
        when(accountRepository.save(any(Account.class))).thenReturn(createTestAccount());

        // Создаем запрос
        AccountTransactionRequest request = new AccountTransactionRequest();
        request.setAccountNumber(ACCOUNT_NUMBER);
        request.setCode(CODE);
        request.setSum(AMOUNT);

        // Вызываем метод и проверяем результат
        Account account = accountService.replenishBalance(request);

        assertNotNull(account);
        assertEquals(AMOUNT, account.getBalance());

        // Проверяем, что метод valid был вызван ровно один раз
        verify(accountRepository, times(1)).findByUuidAndCode(any(UUID.class), any(String.class));

        // Проверяем, что метод save был вызван ровно один раз
        verify(accountRepository, times(1)).save(any(Account.class));

        // Проверяем, что метод saveHistory был вызван ровно один раз
        verify(managerService, times(1)).saveHistory(eq(OperationType.REPLENISHMENT), any(Account.class), eq(AMOUNT));
    }

    @Test
    public void testWritingBalance() {
        // Заглушка для метода valid
        when(accountRepository.findByUuidAndCode(eq(ACCOUNT_UUID), any(String.class))).thenReturn(Optional.of(createTestAccount()));

        // Заглушка для метода save
        when(accountRepository.save(any(Account.class))).thenReturn(createTestAccount());

        // Создаем запрос
        AccountTransactionRequest request = new AccountTransactionRequest();
        request.setAccountNumber(ACCOUNT_NUMBER);
        request.setCode(CODE);
        request.setSum(AMOUNT);

        // Вызываем метод и проверяем результат
        Account account = accountService.writingBalance(request);

        assertNotNull(account);

        // Проверяем, что метод valid был вызван ровно один раз
        verify(accountRepository, times(1)).findByUuidAndCode(eq(ACCOUNT_UUID), any(String.class));

        // Проверяем, что метод save был вызван ровно один раз
        verify(accountRepository, times(1)).save(any(Account.class));

        // Проверяем, что метод saveHistory был вызван ровно один раз
        verify(managerService, times(1)).saveHistory(eq(OperationType.WRITING), any(Account.class), eq(AMOUNT));
    }

    @Test
    public void testTransfer() {
        // Заглушка для метода valid
        when(accountRepository.findByUuidAndCode(any(UUID.class), any(String.class))).thenReturn(Optional.of(createTestAccount()));
        when(accountRepository.findByUuid(any(UUID.class))).thenReturn(Optional.of(createTestAccount()));

        // Заглушка для метода save
        when(accountRepository.save(any(Account.class))).thenReturn(createTestAccount());

        // Создаем запрос
        AccountTransferRequest request = new AccountTransferRequest();
        request.setAccountNumber(ACCOUNT_NUMBER);
        request.setCode(CODE);
        request.setTargetAccountNumber("3110fb9c-e97b-4686-bdb6-2382af7193da");
        request.setSum(AMOUNT);

        // Вызываем метод и проверяем результат
        Account account = accountService.transfer(request);

        assertNotNull(account);

        verify(accountRepository, times(1)).findByUuidAndCode(any(UUID.class), eq(CODE));
        verify(accountRepository, times(2)).save(any(Account.class));

        verify(managerService, times(1)).saveHistory(eq(OperationType.TRANSFER), any(Account.class), any(Account.class), eq(AMOUNT));
        verify(managerService, times(1)).saveHistory(eq(OperationType.RECEIPT_OF_FUNDS), any(Account.class), any(Account.class), eq(AMOUNT));
    }

    private Account createTestAccount() {
        Account account = new Account();
        account.setUuid(UUID.fromString(ACCOUNT_NUMBER));
        account.setCode(CODE);
        account.setCurrency(Currency.valueOf(CURRENCY));
        account.setBalance(100.0);
        account.setUser(createTestUser());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        account.setCreationDate(timestamp);
        account.setModificationDate(timestamp);

        return account;
    }

    private User createTestUser() {
        User user = new User();
        user.setLogin(LOGIN);
        user.setName("Test User");
        user.setCreationDate(new Timestamp(System.currentTimeMillis()));
        user.setModificationDate(new Timestamp(System.currentTimeMillis()));
        return user;
    }
}

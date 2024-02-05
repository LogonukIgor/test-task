package by.logonuk.service.manager;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.Manager;
import by.logonuk.domain.enums.Currency;
import by.logonuk.domain.enums.OperationType;
import by.logonuk.repository.ManagerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceImplTest {
    @InjectMocks
    private ManagerServiceImpl managerService;

    @Mock
    private ManagerRepository managerRepository;

    @Test
    public void testSaveHistoryWithSingleAccount() {
        Account account = createTestAccount();
        Double quantity = 100.0;
        OperationType type = OperationType.REPLENISHMENT;

        managerService.saveHistory(type, account, quantity);

        // Проверяем, что метод save был вызван ровно один раз с правильным аргументом
        ArgumentCaptor<Manager> managerCaptor = ArgumentCaptor.forClass(Manager.class);
        verify(managerRepository, times(1)).save(managerCaptor.capture());

        Manager savedManager = managerCaptor.getValue();
        assertNotNull(savedManager);
        assertEquals(type, savedManager.getOperationType());
        assertEquals(account.getCurrency(), savedManager.getCurrency());
        assertEquals(account, savedManager.getSourceAccount());
        assertEquals(quantity, savedManager.getQuantity());
    }

    @Test
    public void testSaveHistoryWithTwoAccounts() {
        Account sourceAccount = createTestAccount();
        Account targetAccount = createTestAccount();
        Double quantity = 50.0;
        OperationType type = OperationType.TRANSFER;

        managerService.saveHistory(type, sourceAccount, targetAccount, quantity);

        // Проверяем, что метод save был вызван ровно один раз с правильным аргументом
        ArgumentCaptor<Manager> managerCaptor = ArgumentCaptor.forClass(Manager.class);
        verify(managerRepository, times(1)).save(managerCaptor.capture());

        Manager savedManager = managerCaptor.getValue();
        assertNotNull(savedManager);
        assertEquals(type, savedManager.getOperationType());
        assertEquals(sourceAccount.getCurrency(), savedManager.getCurrency());
        assertEquals(sourceAccount, savedManager.getSourceAccount());
        assertEquals(targetAccount.getUuid(), savedManager.getTargetAccount());
        assertEquals(quantity, savedManager.getQuantity());
    }

    private Account createTestAccount() {
        Account account = new Account();
        account.setUuid(UUID.randomUUID());
        account.setCode("TestCode");
        account.setCurrency(Currency.USD);
        account.setBalance(1000.0);
        return account;
    }
}

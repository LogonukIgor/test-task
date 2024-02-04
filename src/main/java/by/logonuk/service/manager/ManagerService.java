package by.logonuk.service.manager;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.enums.OperationType;

public interface ManagerService {

    void saveHistory(OperationType type, Account account, Double quantity);

    void saveHistory(OperationType type, Account sourceAccount, Account targetAccount, Double quantity);
}

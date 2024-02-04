package by.logonuk.service.manager;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.Manager;
import by.logonuk.domain.enums.OperationType;
import by.logonuk.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerServiceImpl implements ManagerService{

    private final ManagerRepository repository;

    @Override
    public void saveHistory(OperationType type, Account account, Double quantity) {
        Manager manager = mapManager(type, account, quantity);

        repository.save(manager);
    }

    @Override
    public void saveHistory(OperationType type, Account sourceAccount, Account targetAccount, Double quantity) {
        Manager manager = mapManager(type, sourceAccount, quantity);
        manager.setTargetAccount(targetAccount.getUuid());

        repository.save(manager);
    }

    private Manager mapManager(OperationType type, Account account, Double quantity){
        Manager manager = new Manager();

        manager.setOperationType(type);
        manager.setCurrency(account.getCurrency());
        manager.setSourceAccount(account);

        manager.setQuantity(quantity);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        manager.setCreationDate(timestamp);
        manager.setModificationDate(timestamp);

        return manager;
    }
}

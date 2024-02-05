package by.logonuk.service.manager;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.Manager;
import by.logonuk.domain.enums.OperationType;
import by.logonuk.domain.mapper.ManagerMapper;
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

    private final ManagerMapper mapper;

    @Override
    public void saveHistory(OperationType type, Account account, Double quantity) {
        Manager manager = mapper.mapManager(type, account, quantity);

        repository.save(manager);
    }

    @Override
    public void saveHistory(OperationType type, Account sourceAccount, Account targetAccount, Double quantity) {
        Manager manager = mapper.mapManager(type, sourceAccount, quantity);
        manager.setTargetAccount(targetAccount.getUuid());

        repository.save(manager);
    }
}

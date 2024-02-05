package by.logonuk.domain.mapper;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.Manager;
import by.logonuk.domain.enums.OperationType;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ManagerMapper {

    public Manager mapManager(OperationType type, Account account, Double quantity){
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

package by.logonuk.domain.mapper;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.Manager;
import by.logonuk.dto.responce.account.AccountManagerResponse;
import by.logonuk.dto.responce.account.AccountResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public AccountResponse mapAccountToResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setUuid(account.getUuid());
        response.setCurrency(account.getCurrency());
        response.setBalance(account.getBalance());

        List<AccountManagerResponse> managerResponses = mapManagers(account.getManagers());
        response.setManagers(managerResponses);

        return response;
    }

    private List<AccountManagerResponse> mapManagers(List<Manager> managers) {
        return managers.stream()
                .map(manager -> {
                    AccountManagerResponse managerResponse = new AccountManagerResponse();
                    managerResponse.setOperationType(manager.getOperationType());
                    managerResponse.setCurrency(manager.getCurrency());
                    managerResponse.setQuantity(manager.getQuantity());
                    managerResponse.setTargetAccount(manager.getTargetAccount());
                    return managerResponse;
                })
                .collect(Collectors.toList());
    }
}

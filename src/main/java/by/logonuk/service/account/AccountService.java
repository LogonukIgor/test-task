package by.logonuk.service.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.dto.account.AccountCreateRequest;

public interface AccountService {

    Account accountCreate(AccountCreateRequest request);
}

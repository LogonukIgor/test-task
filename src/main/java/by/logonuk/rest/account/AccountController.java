package by.logonuk.rest.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.dto.account.AccountCreateRequest;

public interface AccountController {

    Account createAccount(AccountCreateRequest request);
}

package by.logonuk.rest.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.dto.account.AccountCreateRequest;
import by.logonuk.dto.account.AccountTransactionRequest;
import by.logonuk.dto.account.AccountTransferRequest;

public interface AccountController {

    Account createAccount(AccountCreateRequest request);

    Account replenishBalance(AccountTransactionRequest request);

    Account writingBalance(AccountTransactionRequest request);

    Account transfer(AccountTransferRequest request);
}

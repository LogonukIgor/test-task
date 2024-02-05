package by.logonuk.service.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.dto.request.account.AccountCreateRequest;
import by.logonuk.dto.request.account.AccountTransactionRequest;
import by.logonuk.dto.request.account.AccountTransferRequest;
import by.logonuk.dto.request.account.GetAccountInfoRequest;

public interface AccountService {

    Account getInfo(GetAccountInfoRequest request);

    Account accountCreate(AccountCreateRequest request);

    Account replenishBalance(AccountTransactionRequest request);

    Account writingBalance(AccountTransactionRequest request);

    Account transfer(AccountTransferRequest request);
}

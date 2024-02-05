package by.logonuk.rest.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.dto.account.AccountCreateRequest;
import by.logonuk.dto.account.AccountTransactionRequest;
import by.logonuk.dto.account.AccountTransferRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Account", description = "Управление аккаунтами пользователя")
public interface AccountController {

    @Operation(summary = "Создать новый аккаунт для пользователя")
    Account createAccount(AccountCreateRequest request);

    @Operation(summary = "Пополнить аккаунт")
    Account replenishBalance(AccountTransactionRequest request);

    @Operation(summary = "Снять деньги с аккаунта")
    Account writingBalance(AccountTransactionRequest request);

    @Operation(summary = "Перевести деньги на другой аккаунт")
    Account transfer(AccountTransferRequest request);
}

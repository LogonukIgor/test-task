package by.logonuk.rest.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.dto.account.AccountCreateRequest;
import by.logonuk.dto.account.AccountTransactionRequest;
import by.logonuk.dto.account.AccountTransferRequest;
import by.logonuk.service.account.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService service;

    @Override
    @PostMapping("/create")
    public Account createAccount(@RequestBody @Valid AccountCreateRequest request) {
        return service.accountCreate(request);
    }

    @Override
    @PostMapping("/replenish")
    public Account replenishBalance(@RequestBody @Valid AccountTransactionRequest request) {
        return service.replenishBalance(request);
    }

    @Override
    @PostMapping("/writing")
    public Account writingBalance(@RequestBody @Valid AccountTransactionRequest request) {
        return service.writingBalance(request);
    }

    @Override
    @PostMapping("/transfer")
    public Account transfer(@RequestBody @Valid AccountTransferRequest request) {
        return service.transfer(request);
    }
}

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
        Account account = service.accountCreate(request);
        log.info("[createAccount] <-- {}", account);
        return account;
    }

    @Override
    @PostMapping("/replenish")
    public Account replenishBalance(@RequestBody @Valid AccountTransactionRequest request) {
        Account account = service.replenishBalance(request);
        log.info("[replenishBalance] <-- {}", account);
        return account;
    }

    @Override
    @PostMapping("/writing")
    public Account writingBalance(@RequestBody @Valid AccountTransactionRequest request) {
        Account account = service.writingBalance(request);
        log.info("[writingBalance] <-- {}", account);
        return account;
    }

    @Override
    @PostMapping("/transfer")
    public Account transfer(@RequestBody @Valid AccountTransferRequest request) {
        Account account = service.transfer(request);
        log.info("[transfer] <-- {}", account);
        return account;
    }
}

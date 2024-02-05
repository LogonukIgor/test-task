package by.logonuk.rest.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.Manager;
import by.logonuk.domain.mapper.AccountMapper;
import by.logonuk.dto.request.account.AccountCreateRequest;
import by.logonuk.dto.request.account.AccountTransactionRequest;
import by.logonuk.dto.request.account.AccountTransferRequest;
import by.logonuk.dto.request.account.GetAccountInfoRequest;
import by.logonuk.dto.responce.account.AccountResponse;
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

    private final AccountMapper mapper;

    @Override
    @PostMapping("/info")
    public AccountResponse getInfo(@RequestBody @Valid GetAccountInfoRequest request) {
        Account account = service.getInfo(request);
        return mapper.mapAccountToResponse(account);
    }

    @Override
    @PostMapping("/create")
    public Account createAccount(@RequestBody @Valid AccountCreateRequest request) {
        Account account = service.accountCreate(request);

        log.info("[createAccount] <-- {}", account.getUuid());

        return account;
    }

    @Override
    @PostMapping("/replenish")
    public Account replenishBalance(@RequestBody @Valid AccountTransactionRequest request) {
        Account account = service.replenishBalance(request);

        log.info("[replenishBalance] <-- {}, {}", account.getUuid(), account.getManagers().stream()
                .map(manager -> manager.getOperationType() + " - " + manager.getQuantity()).toList());

        return account;
    }

    @Override
    @PostMapping("/writing")
    public Account writingBalance(@RequestBody @Valid AccountTransactionRequest request) {
        Account account = service.writingBalance(request);

        log.info("[writingBalance] <-- {}, {}", account.getUuid(), account.getManagers().stream()
                .map(manager -> manager.getOperationType() + " - " + manager.getQuantity()).toList());

        return account;
    }

    @Override
    @PostMapping("/transfer")
    public Account transfer(@RequestBody @Valid AccountTransferRequest request) {
        Account account = service.transfer(request);

        log.info("[transfer] <-- {}, {}", account.getUuid(), account.getManagers().stream()
                .map(manager -> manager.getOperationType() + " - " + manager.getQuantity()).toList());

        return account;
    }
}

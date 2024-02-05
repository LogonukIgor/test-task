package by.logonuk.rest.account;

import by.logonuk.domain.entity.Account;
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
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService service;

    private final AccountMapper mapper;

    @Override
    @GetMapping("/info")
    public AccountResponse getInfo(@RequestBody @Valid GetAccountInfoRequest request) {
        Account account = service.getInfo(request);
        return mapper.mapAccountToResponse(account);
    }

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

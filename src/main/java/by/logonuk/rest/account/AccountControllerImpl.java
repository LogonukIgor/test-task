package by.logonuk.rest.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.dto.account.AccountCreateRequest;
import by.logonuk.service.account.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package by.logonuk.service.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.User;
import by.logonuk.domain.enums.Currency;
import by.logonuk.domain.enums.OperationType;
import by.logonuk.domain.exceptions.AccountNotFoundException;
import by.logonuk.domain.exceptions.CurrencyMismatchException;
import by.logonuk.domain.exceptions.InsufficientFundsException;
import by.logonuk.domain.exceptions.UserAlreadyExistsException;
import by.logonuk.dto.request.account.AccountCreateRequest;
import by.logonuk.dto.request.account.AccountTransactionRequest;
import by.logonuk.dto.request.account.AccountTransferRequest;
import by.logonuk.dto.request.account.GetAccountInfoRequest;
import by.logonuk.repository.AccountRepository;
import by.logonuk.repository.UserRepository;
import by.logonuk.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final ManagerService managerService;

    private final AccountRepository repository;

    private final UserRepository userRepository;

    @Override
    public Account getInfo(GetAccountInfoRequest request) {
        return valid(request.getAccountNumber());
    }

    @Override
    public Account accountCreate(AccountCreateRequest request) {
        return repository.save(mapAccount(request));
    }

    @Override
    public Account replenishBalance(AccountTransactionRequest request) {
        Account account = valid(request.getAccountNumber(), request.getCode());

        AtomicReference<Double> balance = new AtomicReference<>(account.getBalance());
        balance.updateAndGet(currentBalance -> currentBalance + request.getSum());
        account.setBalance(balance.get());

        account.setModificationDate(new Timestamp(System.currentTimeMillis()));

        managerService.saveHistory(OperationType.REPLENISHMENT, account, request.getSum());

        return repository.save(account);
    }

    @Override
    public Account writingBalance(AccountTransactionRequest request) {
        Account account = valid(request.getAccountNumber(), request.getCode());

        AtomicReference<Double> balance = new AtomicReference<>(account.getBalance());

        if (request.getSum() > account.getBalance()) {
            throw new InsufficientFundsException();
        }

        balance.updateAndGet(existingBalance -> existingBalance - request.getSum());
        account.setBalance(balance.get());

        account.setModificationDate(new Timestamp(System.currentTimeMillis()));

        managerService.saveHistory(OperationType.WRITING, account, request.getSum());

        return repository.save(account);
    }

    @Override
    public Account transfer(AccountTransferRequest request) {
        Account account = valid(request.getAccountNumber(), request.getCode());
        Account targetAccount = valid(request.getTargetAccountNumber());

        if (account.getCurrency() != targetAccount.getCurrency()) {
            throw new CurrencyMismatchException();
        }

        AtomicReference<Double> sourceBalance = new AtomicReference<>(account.getBalance());
        AtomicReference<Double> targetBalance = new AtomicReference<>(targetAccount.getBalance());

        if (request.getSum() > account.getBalance()) {
            throw new InsufficientFundsException();
        }

        sourceBalance.updateAndGet(existingBalance -> existingBalance - request.getSum());
        account.setBalance(sourceBalance.get());

        targetBalance.updateAndGet(existingBalance -> existingBalance + request.getSum());
        targetAccount.setBalance(targetBalance.get());

        Timestamp modificationDate = new Timestamp(System.currentTimeMillis());
        account.setModificationDate(modificationDate);
        targetAccount.setModificationDate(modificationDate);

        managerService.saveHistory(OperationType.TRANSFER, account, targetAccount, request.getSum());
        managerService.saveHistory(OperationType.RECEIPT_OF_FUNDS, targetAccount, account, request.getSum());

        repository.save(targetAccount);
        return repository.save(account);
    }

    private Account mapAccount(AccountCreateRequest request) {
        Account account = new Account();
        account.setCode(request.getCode());
        account.setCurrency(Currency.valueOf(request.getCurrency()));
        account.setBalance(0.0);
        account.setUser(validUser(request.getLogin()));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        account.setCreationDate(timestamp);
        account.setModificationDate(timestamp);

        return account;
    }

    private User validUser(String login) {
        Optional<User> existingUser = userRepository.findByLogin(login);
        if (existingUser.isEmpty()) {
            throw new UserAlreadyExistsException(login);
        }
        return existingUser.get();
    }

    private Account valid(String accountNumber, String code) {
        Optional<Account> accountValid = repository.findByUuidAndCode(UUID.fromString(accountNumber), code);
        return accountValid.orElseThrow(AccountNotFoundException::new);
    }

    private Account valid(String accountNumber) {
        Optional<Account> accountValid = repository.findByUuid(UUID.fromString(accountNumber));
        return accountValid.orElseThrow(AccountNotFoundException::new);
    }
}

package by.logonuk.service.account;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.User;
import by.logonuk.domain.enums.Currency;
import by.logonuk.domain.exceptions.UserAlreadyExistsException;
import by.logonuk.dto.account.AccountCreateRequest;
import by.logonuk.repository.AccountRepository;
import by.logonuk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository repository;

    private final UserRepository userRepository;

    @Override
    public Account accountCreate(AccountCreateRequest request) {
        return repository.save(mapAccount(request));
    }

    private Account mapAccount(AccountCreateRequest request){
        Account account = new Account();
        account.setCode(request.getCode());
        account.setCurrency(Currency.valueOf(request.getCurrency()));
        account.setUser(validUser(request.getLogin()));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        account.setCreationDate(timestamp);
        account.setModificationDate(timestamp);

        return account;
    }

    private User validUser(String login){
        Optional<User> existingUser = userRepository.findByLogin(login);
        if (existingUser.isEmpty()) {
            throw new UserAlreadyExistsException(login);
        }
        return existingUser.get();
    }
}

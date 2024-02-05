package by.logonuk.domain.mapper;

import by.logonuk.domain.entity.Account;
import by.logonuk.domain.entity.User;
import by.logonuk.dto.responce.user.UserAccountForSearchResponse;
import by.logonuk.dto.responce.user.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setLogin(user.getLogin());

        List<UserAccountForSearchResponse> accountResponses = user.getAccounts().stream()
                .map(this::mapAccountToUserAccountForSearchResponse)
                .collect(Collectors.toList());

        userResponse.setAccounts(accountResponses);
        return userResponse;
    }

    private UserAccountForSearchResponse mapAccountToUserAccountForSearchResponse(Account account) {
        UserAccountForSearchResponse accountResponse = new UserAccountForSearchResponse();
        accountResponse.setUuid(account.getUuid());
        accountResponse.setCurrency(account.getCurrency());
        accountResponse.setBalance(account.getBalance());
        return accountResponse;
    }
}

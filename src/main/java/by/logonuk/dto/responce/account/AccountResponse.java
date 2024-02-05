package by.logonuk.dto.responce.account;

import by.logonuk.domain.enums.Currency;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AccountResponse {

    private UUID uuid;

    private Currency currency;

    private Double balance;

    private List<AccountManagerResponse> managers;
}

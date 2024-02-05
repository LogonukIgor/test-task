package by.logonuk.dto.responce.user;

import by.logonuk.domain.enums.Currency;
import lombok.Data;

import java.util.UUID;

@Data
public class UserAccountForSearchResponse {

    private UUID uuid;

    private Currency currency;

    private Double balance;
}

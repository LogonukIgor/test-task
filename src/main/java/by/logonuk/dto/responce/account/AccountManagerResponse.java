package by.logonuk.dto.responce.account;

import by.logonuk.domain.enums.Currency;
import by.logonuk.domain.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountManagerResponse {

    private OperationType operationType;

    private Currency currency;

    private Double quantity;

    private UUID targetAccount;
}

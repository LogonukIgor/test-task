package by.logonuk.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "ДТО для операций: ополнение и снятие денежных средств")
public class AccountTransactionRequest {

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^\\D*\\d{4}\\D*$", message = "Строка должна содержать четыре цифры и больше ничего")
    @Schema(description = "4-ёх значный PIN-code")
    private String code;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "Некорректный формат номера аккаунта")
    @Schema(description = "Номер аккаунта на котором происходит операция")
    private String accountNumber;

    @NotNull(message = "Поле не должно быть пустым")
    @Schema(description = "Сумма перевода")
    private Double sum;
}

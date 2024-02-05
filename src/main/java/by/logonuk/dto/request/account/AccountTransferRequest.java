package by.logonuk.dto.request.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "ДТО для проведения операции по переводу средств между аккаунтами")
public class AccountTransferRequest {

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^\\d{4}$", message = "Строка должна содержать четыре цифры и больше ничего")
    @Schema(description = "4-ёх значный PIN-code")
    private String code;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "Некорректный формат номера аккаунта")
    @Schema(description = "Номер аккаунта с которого происходит перевод")
    private String accountNumber;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "Некорректный формат номера аккаунта")
    @Schema(description = "Номер аккаунта на который происходит перевод")
    private String targetAccountNumber;

    @NotNull(message = "Поле не должно быть пустым")
    @Schema(description = "Сумма перевода")
    private Double sum;
}

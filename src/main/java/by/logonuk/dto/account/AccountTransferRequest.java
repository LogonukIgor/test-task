package by.logonuk.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountTransferRequest {

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^\\D*\\d{4}\\D*$", message = "Строка должна содержать четыре цифры и больше ничего")
    private String code;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "Некорректный формат номера аккаунта")
    private String accountNumber;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "Некорректный формат номера аккаунта")
    private String targetAccountNumber;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^(REPLENISHMENT|WRITING|TRANSFER)$", message = "Недопустимый тип трансфера")
    private String operationType;

    @NotBlank(message = "Поле не должно быть пустым")
    private Double sum;
}

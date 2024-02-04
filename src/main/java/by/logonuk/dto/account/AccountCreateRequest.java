package by.logonuk.dto.account;

import by.logonuk.domain.enums.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountCreateRequest {

    @Size(max = 100, message = "Длина строки 'login' не должна превышать 100 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    private String login;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^\\D*\\d{4}\\D*$", message = "Строка должна содержать четыре цифры и больше ничего")
    private String code;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^(RUB|EUR|USD)$", message = "Недопустимое значение валюты")
    private String currency;
}

package by.logonuk.dto.request.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "ДТО для создания аккаунта для выбранного пользователя")
public class AccountCreateRequest {

    @Size(max = 100, message = "Длина строки 'login' не должна превышать 100 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    @Schema(description = "Уникальный логин пользователя, которому открывается аккаунт")
    private String login;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^\\D*\\d{4}\\D*$", message = "Строка должна содержать четыре цифры и больше ничего")
    @Schema(description = "4-ёх значный PIN-code")
    private String code;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^(RUB|EUR|USD)$", message = "Недопустимое значение валюты")
    @Schema(description = "Тип валюты (RUB|EUR|USD)")
    private String currency;
}

package by.logonuk.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "ДТО для создания пользователя")
public class UserCreateRequest {

    @Size(max = 30, message = "Длина строки 'name' не должна превышать 100 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    @Schema(description = "Имя пользователя")
    private String name;

    @Size(max = 100, message = "Длина строки 'login' не должна превышать 100 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    @Schema(description = "Уникальный логин")
    private String login;
}

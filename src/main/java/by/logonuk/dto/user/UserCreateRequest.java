package by.logonuk.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {

    @Size(max = 30, message = "Длина строки 'name' не должна превышать 100 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;

    @Size(max = 100, message = "Длина строки 'login' не должна превышать 100 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    private String login;
}

package by.logonuk.domain.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String login) {
        super("Пользователь с логином " + login + " уже существует");
    }
}

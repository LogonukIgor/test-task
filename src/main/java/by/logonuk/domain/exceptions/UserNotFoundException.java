package by.logonuk.domain.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String login){
        super("Пользователь с логином " + login + " отсутствует");
    }
}

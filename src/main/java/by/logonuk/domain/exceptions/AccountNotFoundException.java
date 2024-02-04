package by.logonuk.domain.exceptions;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException() {
        super("Проверьте данные: номер аккаунта и PIN");
    }
}

package by.logonuk.domain.exceptions;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException() {
        super("Недостаточно средств для выполнения операции");
    }
}

package by.logonuk.domain.exceptions;

public class CurrencyMismatchException extends RuntimeException{

    public CurrencyMismatchException(){
        super("Типы валют выбранных вами счетов различаются");
    }
}

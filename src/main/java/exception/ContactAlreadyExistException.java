package exception;

public class ContactAlreadyExistException extends Exception{

    public ContactAlreadyExistException(Object contactIdentifier) {
        super(String.format("Contact '%s' already exist.", contactIdentifier.toString()));
    }
}

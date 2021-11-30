package exception;

public class ContactNotFoundException extends Exception {

    public ContactNotFoundException(Object contactIdentifier) {
        super(String.format("Contact '%s' was not found.", contactIdentifier.toString()));
    }
}

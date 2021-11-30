package exception;

public class ContactNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ContactNotFoundException(Object contactIdentifier) {
        super(String.format("Contact '%s' was not found.", contactIdentifier.toString()));
    }
}

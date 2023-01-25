package hei.enjoyvoyage.exception;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String email) {super("email "+email+" have not been found");
    }
}

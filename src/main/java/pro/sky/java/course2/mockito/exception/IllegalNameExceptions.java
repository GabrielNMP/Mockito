package pro.sky.java.course2.mockito.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalNameExceptions extends IllegalArgumentException{
    public IllegalNameExceptions() {
    }

    public IllegalNameExceptions(String s) {
        super(s);
    }

    public IllegalNameExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalNameExceptions(Throwable cause) {
        super(cause);
    }
}

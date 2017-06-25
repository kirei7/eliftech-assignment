package vlad.companies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

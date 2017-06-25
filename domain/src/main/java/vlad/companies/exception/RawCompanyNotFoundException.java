package vlad.companies.exception;

public class RawCompanyNotFoundException extends Exception {
    public RawCompanyNotFoundException(String companyName) {
        super("Company with given name: [" + companyName + "] not found.");
    }
}

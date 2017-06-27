package vlad.companies.factory;

import vlad.companies.entity.Company;
import vlad.companies.entity.CompanyEntity;

public class CompanyEntityFactory implements CompanyFactory {
    @Override
    public Company create() {
        return new CompanyEntity();
    }
}

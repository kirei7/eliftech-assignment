package vlad.companies.service;

import vlad.companies.entity.Company;
import vlad.companies.exception.RawCompanyNotFoundException;

import java.util.Set;

public interface CompanyService {

    Set<Company> getMainCompanies();

    Company save(Company company, String parentName) throws RawCompanyNotFoundException;

    void delete(Company company);

    Company findByName(String name);
}

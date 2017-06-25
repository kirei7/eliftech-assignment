package vlad.companies.dao;

import vlad.companies.entity.Company;

import java.util.Set;

/*Generic type of DAO is based on a type of persistent
* entity we use in application configuration*/
public interface CompanyDao<T extends Company> {

    Set<T> getMainCompanies();

    T save(T company, T parentCompany);

    void delete(T company);

    T findByName(String name);
}

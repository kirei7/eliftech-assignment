package vlad.companies.service;

import vlad.companies.dao.CompanyDao;
import vlad.companies.entity.Company;
import vlad.companies.exception.RawCompanyNotFoundException;
import vlad.companies.factory.CompanyFactory;

import java.util.Set;

public abstract class AbstractCompanyService implements CompanyService{
    private CompanyDao companyDao;
    private CompanyFactory companyFactory;

    public AbstractCompanyService(CompanyDao companyDao, CompanyFactory companyFactory) {
        this.companyDao = companyDao;
        this.companyFactory = companyFactory;
    }

    @Override
    public Set<Company> getMainCompanies() {
        return companyDao.getMainCompanies();
    }

    @Override
    public Company save(Company company, String parentName) throws RawCompanyNotFoundException {
        if (parentName == null || parentName.trim().isEmpty()) return companyDao.save(company, null);
        Company parentCompany = findByName(parentName);
        if (parentCompany == null) throw new RawCompanyNotFoundException(parentName);
        return companyDao.save(company, parentCompany);
    }

    @Override
    public void delete(Company company) {
        companyDao.delete(company);
    }

    @Override
    public Company findByName(String name) {
        if (name == null || name.trim().isEmpty()) return null;
        return companyDao.findByName(name);
    }

    @Override
    public Company newCompany() {
        return companyFactory.create();
    }
}

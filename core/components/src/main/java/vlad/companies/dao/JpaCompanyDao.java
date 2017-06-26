package vlad.companies.dao;

import vlad.companies.entity.CompanyEntity;
import vlad.companies.repository.CompanyEntityRepository;

import java.util.Set;

/*JPA-based DAO, since it is of generic type 'CompanyEntity' which is
* JPA-based persistent entity*/
public class JpaCompanyDao implements CompanyDao<CompanyEntity>{

    private CompanyEntityRepository repository;

    public JpaCompanyDao(CompanyEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<CompanyEntity> getMainCompanies() {
        return repository.findByParentIsNull();
    }

    @Override
    public CompanyEntity save(CompanyEntity company, CompanyEntity parentCompany) {
        parentCompany.addChild(company);
        repository.save(parentCompany);
        return repository.save(company);
    }

    @Override
    public void delete(CompanyEntity company) {
        repository.delete(company);
    }

    @Override
    public CompanyEntity findByName(String name) {
        return repository.findOne(name);
    }
}

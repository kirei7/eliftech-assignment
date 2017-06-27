package vlad.companies.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlad.companies.entity.CompanyEntity;
import vlad.companies.repository.CompanyEntityRepository;

import java.util.Set;

/*JPA-based DAO, since it is of generic type 'CompanyEntity' which is
* JPA-based persistent entity*/
public class JpaCompanyDao implements CompanyDao<CompanyEntity>{

    private final Logger logger = LoggerFactory.getLogger(JpaCompanyDao.class);

    private CompanyEntityRepository repository;

    public JpaCompanyDao(CompanyEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<CompanyEntity> getMainCompanies() {
        return repository.findByParentNameIsNull();
    }

    @Override
    public CompanyEntity save(CompanyEntity company, CompanyEntity parentCompany) {
        if (parentCompany != null) {
            parentCompany.addChild(company);
            repository.save(parentCompany);
            company.setParentName(parentCompany.getName());
        } else company.setParentName(null);
        return repository.save(company);
    }

    @Override
    public void delete(CompanyEntity company) {
        logger.debug(company.toString());
        repository.delete(company);
    }

    @Override
    public CompanyEntity findByName(String name) {
        return repository.findOne(name);
    }
}

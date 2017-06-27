package vlad.companies.service;

import vlad.companies.dao.CompanyDao;
import vlad.companies.factory.CompanyFactory;

import javax.transaction.Transactional;

@Transactional
public class TransactionalCompanyService extends AbstractCompanyService {

    public TransactionalCompanyService(CompanyDao companyDao, CompanyFactory companyFactory) {
        super(companyDao, companyFactory);
    }
}

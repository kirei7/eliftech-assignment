package vlad.companies.service;

import vlad.companies.dao.CompanyDao;

import javax.transaction.Transactional;

@Transactional
public class CompanyServiceImpl extends AbstractCompanyService {

    public CompanyServiceImpl(CompanyDao companyDao) {
        super(companyDao);
    }
}

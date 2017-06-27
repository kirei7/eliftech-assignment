package vlad.companies.config;

import vlad.companies.entity.Company;
import vlad.companies.entity.CompanyEntity;
import vlad.companies.exception.RawCompanyNotFoundException;
import vlad.companies.service.CompanyService;

import java.math.BigDecimal;

public class InMemoryDbInitializer {

    private CompanyService service;

    public InMemoryDbInitializer(CompanyService service) {
        this.service = service;
    }

    public void fillWithInitialData() {
        Company childCompany = new CompanyEntity();
        childCompany.setName("ChildComp");
        childCompany.setEstimatedEarnings(new BigDecimal(1000.5));
        Company childCompany2 = new CompanyEntity();
        childCompany2.setName("ChildComp2");
        childCompany2.setEstimatedEarnings(new BigDecimal(500));


        Company rootCompany1 = new CompanyEntity();
        rootCompany1.setName("Root company inc.");
        rootCompany1.setEstimatedEarnings(new BigDecimal(2000));

        Company rootCompany2 = new CompanyEntity();
        rootCompany2.setName("Another root");
        rootCompany2.setEstimatedEarnings(new BigDecimal(1500));

        try {
            service.save(rootCompany1, null);
            service.save(rootCompany2, null);
            service.save(childCompany, rootCompany1.getName());
            service.save(childCompany2, rootCompany1.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}

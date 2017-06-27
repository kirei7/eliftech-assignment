package vlad.companies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vlad.companies.dao.CompanyDao;
import vlad.companies.factory.CompanyEntityFactory;
import vlad.companies.factory.CompanyFactory;
import vlad.companies.service.CompanyService;
import vlad.companies.service.TransactionalCompanyService;

@Configuration
public class ComponentsConfig {

    @Autowired
    private CompanyDao companyDao;

    @Bean
    public CompanyService companyService() {
        return new TransactionalCompanyService(companyDao, companyFactory());
    }
    @Bean
    public CompanyFactory companyFactory() {
        return new CompanyEntityFactory();
    }

    @Bean
    public InMemoryDbInitializer inMemoryDbInitializer() {
        return new InMemoryDbInitializer(companyService());
    }
}

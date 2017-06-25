package vlad.companies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vlad.companies.dao.CompanyDao;
import vlad.companies.service.CompanyService;
import vlad.companies.service.CompanyServiceImpl;

@Configuration
public class ComponentsConfig {

    @Autowired
    private CompanyDao companyDao;

    @Bean
    public CompanyService companyService() {
        return new CompanyServiceImpl(companyDao);
    }
}

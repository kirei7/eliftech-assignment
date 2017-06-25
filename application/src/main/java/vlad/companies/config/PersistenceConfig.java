package vlad.companies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vlad.companies.dao.CompanyDao;
import vlad.companies.dao.JpaCompanyDao;
import vlad.companies.repository.CompanyEntityRepository;

/*JPA-based configuration for persistence*/
@Configuration
@EnableJpaRepositories("vlad.companies.repository")
public class PersistenceConfig {

    @Autowired
    private CompanyEntityRepository companyEntityRepository;

    @Bean
    public CompanyDao companyDao() {
        return new JpaCompanyDao(companyEntityRepository);
    }
}

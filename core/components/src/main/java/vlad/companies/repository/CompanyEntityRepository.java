package vlad.companies.repository;

import org.springframework.data.repository.CrudRepository;
import vlad.companies.entity.CompanyEntity;

import java.util.Set;

public interface CompanyEntityRepository extends CrudRepository<CompanyEntity, String> {
    Set<CompanyEntity> findByParentNameIsNull();
}

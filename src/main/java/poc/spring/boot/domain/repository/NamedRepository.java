package poc.spring.boot.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import poc.spring.boot.domain.model.AbstractNamedDomainClass;

@NoRepositoryBean
public interface NamedRepository<T extends AbstractNamedDomainClass, ID> extends CrudRepository<T, ID>{
	Iterable<T> findByNameIgnoreCaseContaining(String name);

	Iterable<T> findByDescriptionIgnoreCaseContaining(String description);

	Iterable<T> findByNameIgnoreCaseContainingAndDescriptionIgnoreCaseContaining(String name, String description);

}

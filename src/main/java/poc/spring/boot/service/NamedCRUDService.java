package poc.spring.boot.service;

import java.util.List;

import poc.spring.boot.domain.model.AbstractNamedDomainClass;

public interface NamedCRUDService<T extends AbstractNamedDomainClass> extends CRUDService<T>{
	List<?> findByNameAndDescriptionContaining(String name, String description);
}

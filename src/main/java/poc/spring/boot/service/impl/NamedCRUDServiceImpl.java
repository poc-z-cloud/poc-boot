package poc.spring.boot.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.util.StringUtils;

import poc.spring.boot.domain.model.AbstractNamedDomainClass;
import poc.spring.boot.domain.repository.NamedRepository;
import poc.spring.boot.service.NamedCRUDService;

public abstract class NamedCRUDServiceImpl<T extends AbstractNamedDomainClass> extends CRUDServiceImpl<T> implements NamedCRUDService<T> {

	@Override
	public List<?> findByNameAndDescriptionContaining(String name, String description) {
        List<T> list = new ArrayList<>();
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(description)) {
            list.addAll((Collection<? extends T>) getRepository().findAll());
        }else if (StringUtils.isEmpty(name)) {
            list.addAll((Collection<? extends T>) getRepository().findByDescriptionIgnoreCaseContaining(description));
        	
        }else if (StringUtils.isEmpty(description)) {
            list.addAll((Collection<? extends T>) getRepository().findByNameIgnoreCaseContaining(name));
        }else {
            list.addAll((Collection<? extends T>) getRepository().findByNameIgnoreCaseContainingAndDescriptionIgnoreCaseContaining(name, description));
        	
        }
        return list;
	}

	abstract protected NamedRepository<T,Integer> getRepository();
}

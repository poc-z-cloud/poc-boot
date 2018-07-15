package poc.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import poc.spring.boot.domain.model.Module;
import poc.spring.boot.domain.repository.ModuleRepository;
import poc.spring.boot.domain.repository.NamedRepository;
import poc.spring.boot.service.ModuleService;
 
@Service("moduleService")
@Profile("springdatajpa")
public class ModuleServiceImpl extends NamedCRUDServiceImpl<Module> implements ModuleService {
 
	@Autowired
    private ModuleRepository menuRepository;
 
	@Override
	protected NamedRepository<Module,Integer> getRepository() {
		return menuRepository;
	}
 
}
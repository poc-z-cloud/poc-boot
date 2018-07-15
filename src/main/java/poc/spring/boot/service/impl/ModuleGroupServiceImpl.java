package poc.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import poc.spring.boot.domain.model.ModuleGroup;
import poc.spring.boot.domain.model.Role;
import poc.spring.boot.domain.repository.ModuleGroupRepository;
import poc.spring.boot.domain.repository.NamedRepository;
import poc.spring.boot.domain.repository.RoleRepository;
import poc.spring.boot.service.ModuleGroupService;
import poc.spring.boot.service.RoleService;
 
@Service("moduleGroupService")
@Profile("springdatajpa")
public class ModuleGroupServiceImpl extends NamedCRUDServiceImpl<ModuleGroup> implements ModuleGroupService {
 
	@Autowired
    private ModuleGroupRepository menuGroupRepository;
 
	@Override
	protected NamedRepository<ModuleGroup,Integer> getRepository() {
		return menuGroupRepository;
	}
 
}
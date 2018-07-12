package poc.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import poc.spring.boot.domain.model.MenuGroup;
import poc.spring.boot.domain.model.Role;
import poc.spring.boot.domain.repository.MenuGroupRepository;
import poc.spring.boot.domain.repository.RoleRepository;
import poc.spring.boot.service.MenuGroupService;
import poc.spring.boot.service.RoleService;
 
@Service("menuGroupService")
@Profile("springdatajpa")
public class MenuGroupServiceImpl extends CRUDServiceImpl<MenuGroup> implements MenuGroupService {
 
	@Autowired
    private MenuGroupRepository menuGroupRepository;
 
	@Override
	protected CrudRepository<MenuGroup,Integer> getRepository() {
		return menuGroupRepository;
	}
 
}
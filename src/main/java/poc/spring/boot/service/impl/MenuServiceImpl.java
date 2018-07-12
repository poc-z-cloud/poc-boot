package poc.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import poc.spring.boot.domain.model.Menu;
import poc.spring.boot.domain.repository.MenuRepository;
import poc.spring.boot.service.MenuService;
 
@Service("menuService")
@Profile("springdatajpa")
public class MenuServiceImpl extends CRUDServiceImpl<Menu> implements MenuService {
 
	@Autowired
    private MenuRepository menuRepository;
 
	@Override
	protected CrudRepository<Menu,Integer> getRepository() {
		return menuRepository;
	}
 
}
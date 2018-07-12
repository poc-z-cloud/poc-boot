package poc.spring.boot.controller.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import poc.spring.boot.controller.CRUDController;
import poc.spring.boot.domain.model.Menu;
import poc.spring.boot.service.MenuService;

@Controller
@RequestMapping("/console/menu")
public class MenuController extends CRUDController<Menu, MenuService> {

	@Autowired
	private MenuService menuService;
	
	@Override
	protected String getModuleName() {
		return "menu";
	}

	@Override
	protected MenuService getService() {
		return menuService;
	}

	@Override
	protected Menu getDomainObject() {
		return new Menu();
	}

}

package poc.spring.boot.controller.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import poc.spring.boot.controller.CRUDController;
import poc.spring.boot.controller.NamedCRUDController;
import poc.spring.boot.domain.model.Module;
import poc.spring.boot.domain.model.ModuleIdEnum;
import poc.spring.boot.service.ModuleService;

@Controller
@RequestMapping("/console/module")
public class ModuleController extends NamedCRUDController<Module, ModuleService> {

	@Autowired
	private ModuleService moduleService;
	
	@Override
	protected String getModuleId() {
		return ModuleIdEnum.module.toString();
	}

	@Override
	protected ModuleService getService() {
		return moduleService;
	}

	@Override
	protected Module getDomainObject() {
		return new Module();
	}

}

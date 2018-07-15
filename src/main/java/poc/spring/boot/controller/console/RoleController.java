package poc.spring.boot.controller.console;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poc.spring.boot.controller.CRUDController;
import poc.spring.boot.controller.NamedCRUDController;
import poc.spring.boot.domain.model.ModuleIdEnum;
import poc.spring.boot.domain.model.Role;
import poc.spring.boot.domain.model.User;
import poc.spring.boot.service.RoleService;
import poc.spring.boot.service.UserService;

@Controller
@RequestMapping("/console/role")
public class RoleController extends NamedCRUDController<Role, RoleService> {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@Override
	protected String getModuleId() {
		return ModuleIdEnum.role.toString();
	}

	@Override
	protected RoleService getService() {
		return roleService;
	}

	@Override
	protected Role getDomainObject() {
		return new Role();
	}

	@Override
    @RequestMapping("/edit/{id}")
	public String doEdit(@PathVariable Integer id, Model model) {
		
		Role role = this.getService().getById(id);
		
		List<User> revokedUsers= (List<User>) userService.listAll();
		Iterator<User> it= revokedUsers.iterator();
		
		while (it.hasNext()){
			User user = it.next();
			for (User granted : role.getUsers()){
				if(granted.getId()==user.getId()){
					it.remove();
				}
			}
		}
		
    	model.addAttribute("revokedUsers", revokedUsers);
        return super.doEdit(id,model);
    }


}

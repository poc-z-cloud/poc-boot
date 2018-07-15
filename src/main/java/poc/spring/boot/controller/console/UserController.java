package poc.spring.boot.controller.console;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poc.spring.boot.controller.CRUDController;
import poc.spring.boot.controller.NamedCRUDController;
import poc.spring.boot.domain.model.ModuleIdEnum;
import poc.spring.boot.domain.model.Role;
import poc.spring.boot.domain.model.User;
import poc.spring.boot.service.RoleService;
import poc.spring.boot.service.UserService;

@Controller
@RequestMapping("/console/user")
public class UserController extends NamedCRUDController<User, UserService> {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@Override
	protected String getModuleId() {
		return ModuleIdEnum.user.toString();
	}

	@Override
	protected UserService getService() {
		return userService;
	}

	@Override
	protected User getDomainObject() {
		return new User();
	}

	@Override
    @RequestMapping("/edit/{id}")
	public String doEdit(@PathVariable Integer id, Model model) {
		
		User user = this.getService().getById(id);
		
		user.setPassword("******");//set to 6*; if not 6*, encrypt it and same it, or no change
		
		List<Role> revokedRoles= (List<Role>) roleService.listAll();
		Iterator<Role> it= revokedRoles.iterator();
		
		while (it.hasNext()){
			Role role = it.next();
			for (Role granted : user.getRoles()){
				if(granted.getId()==role.getId()){
					it.remove();
				}
			}
		}
		
    	model.addAttribute("revokedRoles", revokedRoles);
        return super.doEdit(id,model);
    }

	@Override
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String doSave(@Valid User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return toEdit();
        }    	
		if (user.getId()!=null) {
			User oldUser = userService.getById(user.getId());
			
			if ("******".equals(user.getPassword())) {
				user.setPassword(null);
				user.setEncryptedPassword(oldUser.getEncryptedPassword());
			}
		}
    	this.getService().saveOrUpdate(user);
//        return "redirect:/console/product/" + product.getId();
        return "redirect:" + toList();
    }

}

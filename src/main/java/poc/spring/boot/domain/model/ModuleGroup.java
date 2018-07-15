package poc.spring.boot.domain.model;

import javax.persistence.*;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.ArrayList;
import java.util.List;
 
@Entity
public class ModuleGroup extends AbstractNamedDomainClass  {

	private String iconClass; //use bootstrap glyphs or font-awesome icons

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="moduleGroup")
	private List<Module> moduleList;
	
	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public List<Module> getModuleList() {
	    if (this.moduleList == null) {
	    	this.moduleList = new ArrayList<Module>();
	    }		
		return this.moduleList;
	}

//	public void setMenuList(List<Menu> menuList) {
//		this.menuList = menuList;
//	}
//
	public void addModule(final Module module) {
	    if (this.moduleList == null) {
	    	this.moduleList = new ArrayList<Module>();
	    }

	    this.moduleList.add(module);

	    module.setModuleGroup(this);
	  }
	
	public List<String> getModuleIds(){
		List<String> moduleIds = new ArrayList<String>();
		for (Module module: getModuleList()){
			moduleIds.add(module.getModuleId());
		}
		
		return moduleIds;
	}
}
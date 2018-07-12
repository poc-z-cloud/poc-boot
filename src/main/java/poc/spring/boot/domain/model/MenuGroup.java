package poc.spring.boot.domain.model;

import javax.persistence.*;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.ArrayList;
import java.util.List;
 
@Entity
public class MenuGroup extends AbstractNamedDomainClass  {

	private String iconClass; //use bootstrap glyphs or font-awesome icons

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="menuGroup")
	private List<Menu> menuList;
	
	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public List<Menu> getMenuList() {
	    if (this.menuList == null) {
	    	this.menuList = new ArrayList<Menu>();
	    }		
		return this.menuList;
	}

//	public void setMenuList(List<Menu> menuList) {
//		this.menuList = menuList;
//	}
//
	public void addMenu(final Menu menu) {
	    if (this.menuList == null) {
	    	this.menuList = new ArrayList<Menu>();
	    }

	    this.menuList.add(menu);

	    menu.setMenuGroup(this);
	  }
	
	public List<String> getModules(){
		List<String> modules = new ArrayList<String>();
		for (Menu menu: getMenuList()){
			modules.add(menu.getModule());
		}
		
		return modules;
	}
}
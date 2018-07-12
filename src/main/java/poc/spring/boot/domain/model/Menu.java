package poc.spring.boot.domain.model;

import javax.persistence.*;
 
@Entity
public class Menu extends AbstractNamedDomainClass  {

	private String iconClass; //use bootstrap glyphs or font-awesome icons
	private String link;
	private String module;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private MenuGroup menuGroup;

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	
}
package poc.spring.boot.domain.model;

import javax.persistence.*;
 
@Entity
public class Module extends AbstractNamedDomainClass  {

	private String moduleId;//id for menu

	private String iconClass; //use bootstrap glyphs or font-awesome icons
	private String link;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private ModuleGroup moduleGroup;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

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

	public ModuleGroup getModuleGroup() {
		return moduleGroup;
	}

	public void setModuleGroup(ModuleGroup moduleGroup) {
		this.moduleGroup = moduleGroup;
	}

}
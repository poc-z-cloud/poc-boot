package poc.spring.boot.domain.model;

import javax.persistence.*;

@MappedSuperclass
public class AbstractNamedDomainClass extends AbstractDomainClass  {
 
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
}
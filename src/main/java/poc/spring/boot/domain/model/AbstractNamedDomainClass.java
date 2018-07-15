package poc.spring.boot.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class AbstractNamedDomainClass extends AbstractDomainClass  {
	@NotNull
    @Size(min=2, max=255) 
    private String name;

    @Size(max=255) 
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
 
	
}
package poc.spring.boot.domain.model;

import org.springframework.data.annotation.Id;

public class AbstractDomainClass implements DomainObject {

    @Id
    String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
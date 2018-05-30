package poc.spring.boot.domain.repository;

import org.springframework.data.repository.CrudRepository;

import poc.spring.boot.domain.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer>{
	Contact findByFirstName(String firstName);
	Contact findByLastName(String lastName);

	Contact findByFirstNameAndLastName(String firstName, String lastName);

	Contact findByPhoneNumber(String phoneNumber);

}

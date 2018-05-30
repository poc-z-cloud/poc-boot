package poc.spring.boot.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poc.spring.boot.domain.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer>{
	List<Contact> findByFirstName(String firstName);
	List<Contact> findByLastName(String lastName);

	List<Contact> findByFirstNameAndLastName(String firstName, String lastName);

	List<Contact> findByPhoneNumber(String phoneNumber);

}

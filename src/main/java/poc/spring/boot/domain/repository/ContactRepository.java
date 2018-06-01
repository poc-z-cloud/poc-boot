package poc.spring.boot.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import poc.spring.boot.domain.model.Contact;

public interface ContactRepository extends MongoRepository<Contact, String>{
	List<Contact> findByFirstName(String firstName);
	List<Contact> findByLastName(String lastName);

	List<Contact> findByFirstNameAndLastName(String firstName, String lastName);

	List<Contact> findByPhoneNumber(String phoneNumber);

}

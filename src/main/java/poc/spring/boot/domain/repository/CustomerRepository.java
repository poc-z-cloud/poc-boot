package poc.spring.boot.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import poc.spring.boot.domain.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
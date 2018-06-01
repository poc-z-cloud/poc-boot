package poc.spring.boot.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poc.spring.boot.domain.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
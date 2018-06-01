package poc.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import poc.spring.boot.domain.model.Customer;
import poc.spring.boot.domain.repository.CustomerRepository;
import poc.spring.boot.service.CustomerService;
 
@Service("customerService")
public class CustomerServiceImpl extends CRUDServiceImpl<Customer> implements CustomerService {
 
	@Autowired
    private CustomerRepository customerRepository;
 
	@Override
	protected MongoRepository<Customer, String> getRepository() {
		return customerRepository;
	}
 
}
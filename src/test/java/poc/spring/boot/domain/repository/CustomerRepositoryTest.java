package poc.spring.boot.domain.repository;


import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import poc.spring.boot.configuration.RepositoryConfiguration;
import poc.spring.boot.domain.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Before
    public void setUp() throws Exception {
    }
 
    @Test
    public void testInsertData(){
    	Customer customer = new Customer("Jon", "Snow");
    	
    	customer = customerRepository.save(customer);
    	
    	System.out.println("customerId: " + customer.getId());
    }

    @Test
    public void testUpdateData(){
        /*Test data retrieval*/
//        List<Contact> contactList = contactRepository.findByFirstName("FIRST Name");
//        assertNotNull(contactList);
//
//        for (Contact contact: contactList){
//            contact.setFirstName("First Name Updated");
//        }
//        
//        contactRepository.saveAll(contactList);

    }

    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        List<Customer> contactList = customerRepository.findByLastName("Snow");
        
        assertNotNull(contactList);
//        assertEquals("Snow", contactA.getLastName());
        
        
        /*Get all products, list should only have two*/
        Iterable<Customer> customers = customerRepository.findAll();
        for(Customer o : customers){
        	System.out.println("Customer: " + o);
        }
    }
}

package poc.spring.boot.domain.repository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import poc.spring.boot.configuration.RepositoryConfiguration;
import poc.spring.boot.domain.model.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;
    @Before
    public void setUp() throws Exception {
    }
 
    @Test
    public void testInsertData(){
        /*Test data retrieval*/
    	Contact contact = new Contact();
    	
    	contact.setFirstName("First Name");
    	contact.setLastName("Last Name");
    	contact.setBirthday(new Date());
    	contact.setPhoneNumber("416-3698742");
    	
    	contact = contactRepository.save(contact);
    	
    	System.out.println("contactId: " + contact.getId());
    }

    @Test
    public void testUpdateData(){
        /*Test data retrieval*/
        List<Contact> contactList = contactRepository.findByFirstName("FIRST Name");
        assertNotNull(contactList);

        for (Contact contact: contactList){
            contact.setFirstName("First Name Updated");
        }
        
        contactRepository.saveAll(contactList);

    }

    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        List<Contact> contactList = contactRepository.findByFirstName("Jon");
        
        assertNotNull(contactList);
//        assertEquals("Snow", contactA.getLastName());
        
        contactList = contactRepository.findByFirstNameAndLastName("Jon", "Snow");
        for (Contact contact: contactList){
        	
        	System.out.println("Found: " + contact.getName());
        	assertEquals("Snow", contact.getLastName());
        }
        
        /*Get all products, list should only have two*/
        Iterable<Contact> contacts = contactRepository.findAll();
        for(Contact o : contacts){
        	System.out.println("name: " + o.getName());
        }
    }
}

package poc.spring.boot.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import poc.spring.boot.domain.model.Contact;
import poc.spring.boot.domain.model.Product;
import poc.spring.boot.domain.model.UserDetailsImpl;
import poc.spring.boot.domain.repository.ContactRepository;
import poc.spring.boot.domain.repository.ProductRepository;
import poc.spring.boot.domain.repository.UserDetailsRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@Profile("dev")
public class SpringDataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired	private ProductRepository productRepository;
	@Autowired	private ContactRepository contactRepository;
	@Autowired	private UserDetailsRepository userDetailsReporitory;

    private Logger log = LoggerFactory.getLogger(SpringDataBootstrap.class);

    
	@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
		
        loadProducts();
        loadContact();
        
        loadUserDetails();
    }

    private void loadProducts() {
    	productRepository.deleteAll();
    	
        Product shirt = new Product();
        shirt.setDescription("Spring Framework Guru Shirt");
        shirt.setPrice(new BigDecimal("18.95"));
        shirt.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
        productRepository.save(shirt);

        log.info("Saved Shirt - id: " + shirt.getId());

        Product mug = new Product();
        mug.setDescription("Spring Framework Guru Mug");
        mug.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug.setPrice(new BigDecimal("11.95"));
        productRepository.save(mug);

        log.info("Saved Mug - id:" + mug.getId());
    }


    private void loadContact() {
    	contactRepository.deleteAll();
    	
    	Contact contact = new Contact();
    	contact.setFirstName("Jon");
    	contact.setLastName("Snow");
    	contact.setBirthday(new Date());
    	
    	contactRepository.save(contact);

        log.info("Saved contact - id: " + contact.getId());

    }
    
    private void loadUserDetails() {
    	userDetailsReporitory.deleteAll();
    	
    	String[] roleForUser ={"USER"};
    	String[] roleForAdmin ={"ADMIN"}; 

    	String passwordUser = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("user");
    	String passwordAdmin = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin");

    	UserDetailsImpl userDetails = new UserDetailsImpl("user",passwordUser,roleForUser);
    	userDetailsReporitory.save(userDetails);
        log.info("Saved User - id: " + userDetails.getId());

        userDetails = new UserDetailsImpl("admin",passwordAdmin,roleForAdmin);
    	userDetailsReporitory.save(userDetails);
        log.info("Saved User - id: " + userDetails.getId());

    }

}
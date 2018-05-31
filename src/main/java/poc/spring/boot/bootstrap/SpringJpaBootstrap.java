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
import org.springframework.stereotype.Component;

import poc.spring.boot.domain.model.Contact;
import poc.spring.boot.domain.model.Product;
import poc.spring.boot.domain.model.Role;
import poc.spring.boot.domain.model.User;
import poc.spring.boot.domain.repository.ContactRepository;
import poc.spring.boot.domain.repository.ProductRepository;
import poc.spring.boot.service.RoleService;
import poc.spring.boot.service.UserService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@Profile("dev")
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired	private ProductRepository productRepository;
	@Autowired	private ContactRepository contactRepository;
	@Autowired	private UserService userService;
	@Autowired	private RoleService roleService;

    private Logger log = LoggerFactory.getLogger(ProductLoader.class);

    
//	@Autowired
//	@Qualifier("userService")
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
//
//	
//	@Autowired
//	public void setProductRepository(ProductRepository productRepository) {
//		this.productRepository = productRepository;
//	}
//
//
//	@Autowired
//	@Qualifier("roleService")
//	public void setRoleService(RoleService roleService) {
//		this.roleService = roleService;
//	}


	@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
		
        loadUsers();
        loadRoles();
        assignUsersToUserRole();
        assignUsersToAdminRole();

        loadProducts();
        loadContact();
    }

    private void loadProducts() {
        Product shirt = new Product();
        shirt.setDescription("Spring Framework Guru Shirt");
        shirt.setPrice(new BigDecimal("18.95"));
        shirt.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
        shirt.setProductId("235268845711068308");
        productRepository.save(shirt);

        log.info("Saved Shirt - id: " + shirt.getId());

        Product mug = new Product();
        mug.setDescription("Spring Framework Guru Mug");
        mug.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug.setProductId("168639393495335947");
        mug.setPrice(new BigDecimal("11.95"));
        productRepository.save(mug);

        log.info("Saved Mug - id:" + mug.getId());
    }


    private void loadContact() {
    	Contact contact = new Contact();
    	contact.setFirstName("Jon");
    	contact.setLastName("Snow");
    	contact.setBirthday(new Date());
    	
    	contactRepository.save(contact);

        log.info("Saved contact - id: " + contact.getContactId());

    }
    
    private void loadUsers() {
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("user");
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("admin");
        userService.saveOrUpdate(user2);

    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);
        log.info("Saved role" + role.getRole());
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
        log.info("Saved role" + adminRole.getRole());
    }
    private void assignUsersToUserRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        for(Role role: roles){
        	if (role.getRole().equalsIgnoreCase("USER")){
        		for (User user:users){
        			if (user.getUsername().equals("user")){
        				user.addRole(role);
        				userService.saveOrUpdate(user);
        			}
        		}
        	}
        }
        
    }
    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        for(Role role: roles){
        	if (role.getRole().equalsIgnoreCase("ADMIN")){
        		for (User user:users){
        			if (user.getUsername().equals("admin")){
        				user.addRole(role);
        				userService.saveOrUpdate(user);
        			}
        		}
        	}
        }
    }
}
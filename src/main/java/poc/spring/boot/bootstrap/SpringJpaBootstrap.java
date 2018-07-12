package poc.spring.boot.bootstrap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import poc.spring.boot.domain.model.Contact;
import poc.spring.boot.domain.model.Menu;
import poc.spring.boot.domain.model.MenuGroup;
import poc.spring.boot.domain.model.Product;
import poc.spring.boot.domain.model.Role;
import poc.spring.boot.domain.model.User;
import poc.spring.boot.domain.repository.ContactRepository;
import poc.spring.boot.domain.repository.MenuGroupRepository;
import poc.spring.boot.domain.repository.MenuRepository;
import poc.spring.boot.domain.repository.ProductRepository;
import poc.spring.boot.domain.repository.RoleRepository;
import poc.spring.boot.domain.repository.UserRepository;

@Component
@Profile("dev")
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired	private ProductRepository productRepository;
	@Autowired	private ContactRepository contactRepository;
	@Autowired	private UserRepository userRepository;
	@Autowired	private RoleRepository roleRepository;
	@Autowired	private MenuGroupRepository menuGroupRepository;
	@Autowired	private MenuRepository menuRepository;

    private Logger log = LoggerFactory.getLogger(SpringJpaBootstrap.class);

    
	@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
		
        loadUsers();
        loadRoles();
        assignUsersToUserRole();
        assignUsersToAdminRole();

        loadProducts();
        loadContact();

        loadMenus();
    }

    private void loadMenus() {
    	MenuGroup menuGroup = new MenuGroup();
    	menuGroup.setName("Show Case");
    	menuGroup.setIconClass("glyphicon glyphicon-cog");
    	
    	Menu menu = new Menu();
    	menu.setName("Menu Group");
    	menu.setIconClass("glyphicon glyphicon-star");
    	menu.setLink("/console/menu-group-list");
    	menu.setModule("menuGroup");
    	menuGroup.addMenu(menu);
    	
    	menu = new Menu();
    	menu.setName("Menu");
    	menu.setIconClass("glyphicon glyphicon-star-empty");
    	menu.setLink("/console/menu-list");
    	menu.setModule("menu-list");
    	menuGroup.addMenu(menu);
		
    	menu = new Menu();
    	menu.setName("New Menu");
    	menu.setIconClass("glyphicon glyphicon-star-empty");
    	menu.setLink("/console/menu/list");
    	menu.setModule("menu");
    	menuGroup.addMenu(menu);

    	menuGroupRepository.save(menuGroup);
	}

	private void loadProducts() {
    	productRepository.deleteAll();
    	
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
    	contactRepository.deleteAll();
    	
    	Contact contact = new Contact();
    	contact.setFirstName("Jon");
    	contact.setLastName("Snow");
    	contact.setBirthday(new Date());
    	
    	contactRepository.save(contact);

        log.info("Saved contact - id: " + contact.getId());

    }
    
    private void loadUsers() {
    	userRepository.deleteAll();
    	
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("user");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("admin");
        userRepository.save(user2);

    }

    private void loadRoles() {
    	roleRepository.deleteAll();
    	
        Role role = new Role();
        role.setRole("USER");
        roleRepository.save(role);
        log.info("Saved role" + role.getRole());
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleRepository.save(adminRole);
        log.info("Saved role" + adminRole.getRole());
    }
    private void assignUsersToUserRole() {
        List<Role> roles = (List<Role>) roleRepository.findAll();
        List<User> users = (List<User>) userRepository.findAll();

        for(Role role: roles){
        	if (role.getRole().equalsIgnoreCase("USER")){
        		for (User user:users){
        			if (user.getUsername().equals("user")){
        				user.addRole(role);
        				userRepository.save(user);
        			}
        		}
        	}
        }
        
    }
    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleRepository.findAll();
        List<User> users = (List<User>) userRepository.findAll();

        for(Role role: roles){
        	if (role.getRole().equalsIgnoreCase("ADMIN")){
        		for (User user:users){
        			if (user.getUsername().equals("admin")){
        				user.addRole(role);
        				userRepository.save(user);
        			}
        		}
        	}
        }
    }
}
package poc.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import poc.spring.boot.domain.model.User;
import poc.spring.boot.domain.repository.NamedRepository;
import poc.spring.boot.domain.repository.UserRepository;
//import poc.spring.boot.service.EncryptionService;
import poc.spring.boot.service.UserService;

@Service("userService")
@Profile("springdatajpa")
public class UserServiceImpl extends NamedCRUDServiceImpl<User> implements UserService{

	@Autowired
    private UserRepository userRepository;
//	@Autowired
//	private EncryptionService encryptionService;

	@Override
	protected NamedRepository<User,Integer> getRepository() {
		return userRepository;
	}

	@Override
	public User findByUsername(String name) {
		return userRepository.findByName(name);
	}

    @Override
    public User saveOrUpdate(User domainObject) {
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(domainObject.getPassword()));
        }
        return userRepository.save(domainObject);
    }
}

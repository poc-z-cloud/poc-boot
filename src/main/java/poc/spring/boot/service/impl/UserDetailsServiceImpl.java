package poc.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import poc.spring.boot.domain.model.UserDetailsImpl;
import poc.spring.boot.domain.repository.UserDetailsRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl extends CRUDServiceImpl<UserDetailsImpl> implements UserDetailsService {

	@Autowired
    private UserDetailsRepository userDetailsRepository;
 
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserDetails userDetails = userDetailsRepository.findByUsername(username);
    	
    	if (userDetails==null)  throw new UsernameNotFoundException(username + " not found");
    	
    	return userDetails;
    }

	@Override
	protected MongoRepository<UserDetailsImpl, String> getRepository() {
		return userDetailsRepository;
	}
}
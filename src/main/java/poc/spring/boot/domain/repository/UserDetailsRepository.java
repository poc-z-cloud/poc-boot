package poc.spring.boot.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import poc.spring.boot.domain.model.UserDetailsImpl;

public interface UserDetailsRepository extends MongoRepository<UserDetailsImpl, String>{
	UserDetailsImpl findByUsername(String username);
}
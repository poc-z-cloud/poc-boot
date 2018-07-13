package poc.spring.boot.domain.repository;

import org.springframework.data.repository.CrudRepository;

import poc.spring.boot.domain.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
    User findByName(String name);
}
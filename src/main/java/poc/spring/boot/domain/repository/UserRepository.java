package poc.spring.boot.domain.repository;

import poc.spring.boot.domain.model.User;

public interface UserRepository extends NamedRepository<User, Integer>{
    User findByName(String name);
}
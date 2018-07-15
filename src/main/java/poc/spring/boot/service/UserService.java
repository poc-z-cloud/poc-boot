package poc.spring.boot.service;

import poc.spring.boot.domain.model.User;

public interface UserService extends NamedCRUDService<User> {

    User findByUsername(String username);

}
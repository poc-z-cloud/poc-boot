package poc.spring.boot.domain.repository;

import org.springframework.data.repository.CrudRepository;

import poc.spring.boot.domain.model.MenuGroup;
public interface MenuGroupRepository extends CrudRepository<MenuGroup, Integer>{
}
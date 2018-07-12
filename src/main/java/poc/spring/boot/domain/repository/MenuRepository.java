package poc.spring.boot.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poc.spring.boot.domain.model.Menu;
public interface MenuRepository extends CrudRepository<Menu, Integer>{
	List<Menu> findByName(String name);

}
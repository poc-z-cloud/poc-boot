package poc.spring.boot.domain.repository;

import org.springframework.data.repository.CrudRepository;

import poc.spring.boot.domain.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
	Order findByProductName(String productName);
}

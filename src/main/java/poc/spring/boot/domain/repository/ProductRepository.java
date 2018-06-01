package poc.spring.boot.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import poc.spring.boot.domain.model.Product;
 
public interface ProductRepository extends MongoRepository<Product, String>{
}
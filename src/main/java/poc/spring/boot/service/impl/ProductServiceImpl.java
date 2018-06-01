package poc.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import poc.spring.boot.domain.model.Product;
import poc.spring.boot.domain.repository.ProductRepository;
import poc.spring.boot.service.ProductService;

@Service("productService")
public class ProductServiceImpl extends CRUDServiceImpl<Product> implements ProductService {

	@Autowired
    private ProductRepository productRepository;
	
	@Override
	protected MongoRepository<Product, String> getRepository() {
		return productRepository;
	}
}

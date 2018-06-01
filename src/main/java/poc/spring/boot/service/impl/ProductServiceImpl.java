package poc.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import poc.spring.boot.domain.model.Product;
import poc.spring.boot.domain.repository.ProductRepository;
import poc.spring.boot.service.ProductService;

@Service("productService")
public class ProductServiceImpl extends CRUDServiceImpl<Product> implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	protected CrudRepository<Product, Integer> getRepository() {
		return productRepository;
	}

	
}

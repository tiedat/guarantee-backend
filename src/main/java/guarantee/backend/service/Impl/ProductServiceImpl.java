package guarantee.backend.service.Impl;

import guarantee.backend.model.Product;
import guarantee.backend.repositories.ProductRepository;
import guarantee.backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product createOrUpdate(Product createProduct) {
        return productRepository.saveAndFlush(createProduct);
    }
}

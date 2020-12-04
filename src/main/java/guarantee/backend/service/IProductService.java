package guarantee.backend.service;

import guarantee.backend.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    void delete(Long productId);
    Product createOrUpdate(Product createProduct);
}

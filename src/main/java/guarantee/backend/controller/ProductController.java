package guarantee.backend.controller;

import guarantee.backend.model.Product;
import guarantee.backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/createOrUpdate")
    public ResponseEntity<Product> createOrUpdate(@RequestBody Product createProduct) {
        return new ResponseEntity<>(productService.createOrUpdate(createProduct), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity delete(@PathVariable Long productId) {
        productService.delete(productId);
        return new ResponseEntity(HttpStatus.OK);
    }

}

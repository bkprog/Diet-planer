package dietplaner.example.dietplaner.product.controller;

import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.models.ProductDTO;
import dietplaner.example.dietplaner.product.repository.ProductRepository;
import dietplaner.example.dietplaner.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping("/product/add")
    public Product addProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }

    @DeleteMapping("/product/deleteall")
    public void deleteAllProducts(){
        productRepository.deleteAll();
    }

    @GetMapping("/product/getall")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}

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
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }

    @DeleteMapping("/deleteall")
    public void deleteAllProducts(){
        productRepository.deleteAll();
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestBody Long productId){
        productService.deleteProduct(productId);
    }

    @GetMapping("/getall")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


}

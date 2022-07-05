package dietplaner.example.dietplaner.product.service;

import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.exceptions.ProductAlreadyExistException;
import dietplaner.example.dietplaner.product.models.ProductDTO;
import dietplaner.example.dietplaner.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private void validateWithProductName(String productName){
            if(productRepository.findByProductName(productName).isPresent()){
                throw new ProductAlreadyExistException();
    }}

    public Product addProduct(ProductDTO productDTO){
        validateWithProductName(productDTO.getProductName());

        return productRepository.save(Product.of(productDTO));
    }




}
package dietplaner.example.dietplaner.product.service;

import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.exceptions.ProductAlreadyExistException;
import dietplaner.example.dietplaner.product.exceptions.ProductNotExistException;
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

    private void validateIfProductExistById(Long productId){
        productRepository.findById(productId).stream().findFirst().orElseThrow(ProductNotExistException::new);
    }

    public Product addProduct(ProductDTO productDTO){
        System.out.println("break");
        validateWithProductName(productDTO.getProductName());

        return productRepository.save(Product.of(productDTO));
    }

    public void deleteProduct(Long productId){
        validateIfProductExistById(productId);
        productRepository.deleteById(productId);
    }


}
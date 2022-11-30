package dietplaner.example.dietplaner.product.service;

import dietplaner.example.dietplaner.alergen.exceptions.AlergenNotExistException;
import dietplaner.example.dietplaner.comment.repository.CommentRepository;
import dietplaner.example.dietplaner.comment.service.CommentService;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.exceptions.ProductAlreadyExistException;
import dietplaner.example.dietplaner.product.exceptions.ProductNotExistException;
import dietplaner.example.dietplaner.product.models.ProductDTO;
import dietplaner.example.dietplaner.product.repository.ProductRepository;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    void addProduct_should_add() {
        //given
        ProductDTO productDTO= new ProductDTO("produkt",10L,10L,10L,10L);
        //when
        Product result = productService.addProduct(productDTO);
        //then
        Assertions.assertEquals(productDTO.getProductName(),result.getProductName());
    }

    @Test
    void addProduct_should_throw_ProductAlreadyExistException(){
        //given
        ProductDTO productDTO= new ProductDTO("produkt",10L,10L,10L,10L);
        productRepository.save(Product.of(productDTO));

        //when
        Exception thrown=Assertions.assertThrows(ProductAlreadyExistException.class,()->{
            productService.addProduct(productDTO);
        });
        //then
        Assertions.assertEquals("Wprowadzona nazwa produktu jest już zajęta, wybierz inny",thrown.getMessage());
    }



    @Test
    void findProductById_should_find() {
        //given
        ProductDTO productDTO= new ProductDTO("produkt",10L,10L,10L,10L);
        Product expected= productRepository.save(Product.of(productDTO));
        //when
        Product result = productService.findProductbyId(expected.getId());
        //then
        Assertions.assertEquals(expected.getProductName(),result.getProductName());
    }

    @Test
    void findProductById_should_throw_ProductNotExistException(){
        //given

        //when
        Exception thrown=Assertions.assertThrows(ProductNotExistException.class,()->{
            productService.findProductbyId(1L);
        });
        //then
        Assertions.assertEquals("Produkt nie istnieje",thrown.getMessage());
    }

    @Test
    void deleteProduct_should_delete() {
        //given
        ProductDTO productDTO1= new ProductDTO("produkt1",10L,10L,10L,10L);
        ProductDTO productDTO2= new ProductDTO("produkt2",10L,10L,10L,10L);
        Product product1=productRepository.save(Product.of(productDTO1));
        Product product2=productRepository.save(Product.of(productDTO2));
        //when
        productService.deleteProduct(product1.getId());
        //then
        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(product2.getProductName(),products.get(0).getProductName());
        Assertions.assertNotEquals(product1.getProductName(),products.get(0).getProductName());
    }

    @Test
    void deleteProduct_should_throw_ProductNotExistException(){
        //given

        //when
        Exception thrown=Assertions.assertThrows(ProductNotExistException.class,()->{
            productService.deleteProduct(1L);
        });
        //then
        Assertions.assertEquals("Produkt nie istnieje",thrown.getMessage());
    }
}
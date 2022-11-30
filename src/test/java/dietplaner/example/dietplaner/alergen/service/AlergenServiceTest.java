package dietplaner.example.dietplaner.alergen.service;

import dietplaner.example.dietplaner.alergen.entity.Alergen;
import dietplaner.example.dietplaner.alergen.exceptions.*;
import dietplaner.example.dietplaner.alergen.models.AlergenDTO;
import dietplaner.example.dietplaner.alergen.models.UpdateAlergenListForProductDTO;
import dietplaner.example.dietplaner.alergen.models.UpdateAlergenListForUserDTO;
import dietplaner.example.dietplaner.alergen.repository.AlergenRepository;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.exceptions.ProductNotExistException;
import dietplaner.example.dietplaner.product.repository.ProductRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.exceptions.UserNotExistException;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlergenServiceTest {


    @Autowired
    private AlergenRepository alergenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlergenService alergenService;
    @Autowired
    private ProductRepository productRepository;


    @AfterEach
    public void tearDown(){
        userRepository.deleteAll();
        productRepository.deleteAll();
        alergenRepository.deleteAll();
    }


    @Test
    void addAlergen_should_add() {

        //given
       AlergenDTO alergenDTO= new AlergenDTO("miód");
        //when
        Alergen alergen=alergenService.addAlergen(alergenDTO);
        //then
        Assertions.assertEquals("miód",alergen.getName());
    }

    @Test
    void addAlergen_should_throw_exception() {

        //given
        Alergen firstAlergen= new Alergen("miód");
        alergenRepository.save(firstAlergen);
        AlergenDTO alergenDTO= new AlergenDTO("miód");
        //when
        Exception thrown=Assertions.assertThrows(AlergenAlreadyExistException.class,()->{
            alergenService.addAlergen(alergenDTO);
        });
        //then
        Assertions.assertEquals("Ten alergen juz istnieje",thrown.getMessage());
    }


    @Test
    void getAll() {
        //given
        List<Alergen> alergenList= new ArrayList<>();
        alergenList.add(new Alergen("alergen1"));
        alergenList.add(new Alergen("alergen2"));
        alergenRepository.saveAll(alergenList);
        //when
        List<Alergen> result = alergenService.getAll();
        //then
        Assertions.assertEquals(alergenList.get(0).getName(),result.get(0).getName());
        Assertions.assertEquals(alergenList.get(1).getName(),result.get(1).getName());

    }


    @Test
    void setAlergenOfUser_should_set() {
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("shouldBeReturned"));
        Alergen alergen2= alergenRepository.save(new Alergen("shouldntBeReturned"));
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));
        UpdateAlergenListForUserDTO updateAlergenListForUserDTO= new UpdateAlergenListForUserDTO(user.getUserId(),alergen1.getAlergenId());
        //when
        DefaultUser updatedUser= alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        //then
        Assertions.assertFalse(updatedUser.getAlergens().isEmpty());
        Assertions.assertEquals(updatedUser.getAlergens().get(0).getName(),alergen1.getName());
        Assertions.assertNotEquals(updatedUser.getAlergens().get(0).getName(),alergen2.getName());
    }

    @Test
    void setAlergenOfUser_should_throw_userNotExistException(){
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("shouldBeReturned"));


        UpdateAlergenListForUserDTO updateAlergenListForUserDTO= new UpdateAlergenListForUserDTO(1L,alergen1.getAlergenId());
        //when
        Exception thrown=Assertions.assertThrows(UserNotExistException.class,()->{
            alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        });
        //then
        Assertions.assertEquals("Uzytkownik o tym id nie istnieje",thrown.getMessage());
    }

    @Test
    void setAlergenOfUser_should_throw_AlergenNotExistException(){
        //given
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));
        UpdateAlergenListForUserDTO updateAlergenListForUserDTO= new UpdateAlergenListForUserDTO(user.getUserId(), 1L);

        //when
        Exception thrown=Assertions.assertThrows(AlergenNotExistException.class,()->{
            alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        });
        //then
        Assertions.assertEquals("Alergen nie istnieje",thrown.getMessage());
    }

    @Test
    void setAlergenOfUser_should_throw_UserAlreadyHaveThisAlergenSavedException(){
        Alergen alergen1= alergenRepository.save(new Alergen("shouldBeReturned"));
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));
        UpdateAlergenListForUserDTO updateAlergenListForUserDTO= new UpdateAlergenListForUserDTO(user.getUserId(),alergen1.getAlergenId());
        alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        //when
        Exception thrown=Assertions.assertThrows(UserAlreadyHaveThisAlergenSavedException.class,()->{
            alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        });
        //then
        Assertions.assertEquals("Ten uzytkownik ma już zapisany ten alergen",thrown.getMessage());

    }


    @Test
    void removeAlergenFromUser_should_remove() {
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("shouldBeReturned"));
        Alergen alergen2= alergenRepository.save(new Alergen("shouldntBeReturned"));
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));
        UpdateAlergenListForUserDTO updateAlergenListForUserDTO= new UpdateAlergenListForUserDTO(user.getUserId(),alergen1.getAlergenId());
        alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        updateAlergenListForUserDTO.setAlergenId(alergen2.getAlergenId());
        alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        //when
        DefaultUser result= alergenService.removeAlergenFromUser(updateAlergenListForUserDTO);
        //then
        Assertions.assertEquals(1,result.getAlergens().size());
        Assertions.assertEquals(alergen1.getName(),result.getAlergens().get(0).getName());
        Assertions.assertNotEquals(alergen2.getName(),result.getAlergens().get(0).getName());
    }

    @Test
    void removeAlergenfromUser_should_throw_UserNotExistException(){
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("shouldBeReturned"));
        Alergen alergen2= alergenRepository.save(new Alergen("shouldntBeReturned"));
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));
        UpdateAlergenListForUserDTO updateAlergenListForUserDTO= new UpdateAlergenListForUserDTO(user.getUserId(),alergen1.getAlergenId());
        alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        updateAlergenListForUserDTO.setAlergenId(alergen2.getAlergenId());
        alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        updateAlergenListForUserDTO.setUserId(updateAlergenListForUserDTO.getUserId()+1);
        //when
        Exception thrown=Assertions.assertThrows(UserNotExistException.class,()->{
            alergenService.removeAlergenFromUser(updateAlergenListForUserDTO);
        });
        //then
        Assertions.assertEquals("Uzytkownik o tym id nie istnieje",thrown.getMessage());
    }

    @Test
    void removeAlergenfromUser_should_throw_AlergenNotExistException(){
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("shouldBeReturned"));
        Alergen alergen2= alergenRepository.save(new Alergen("shouldntBeReturned"));
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));
        UpdateAlergenListForUserDTO updateAlergenListForUserDTO= new UpdateAlergenListForUserDTO(user.getUserId(),alergen1.getAlergenId());
        alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        updateAlergenListForUserDTO.setAlergenId(alergen2.getAlergenId());
        alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
        updateAlergenListForUserDTO.setAlergenId(alergen2.getAlergenId()+1);
        //when
        Exception thrown=Assertions.assertThrows(AlergenNotExistException.class,()->{
            alergenService.removeAlergenFromUser(updateAlergenListForUserDTO);
        });
        //then
        Assertions.assertEquals("Alergen nie istnieje",thrown.getMessage());
    }

    @Test
    void removeAlergenfromUser_should_throw_UserDoesntHaveThisAlergenSavedException(){
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("shouldBeReturned"));
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));
        UpdateAlergenListForUserDTO updateAlergenListForUserDTO= new UpdateAlergenListForUserDTO(user.getUserId(),alergen1.getAlergenId());

        //when
        Exception thrown=Assertions.assertThrows(UserDoesntHaveThisAlergenSavedException.class,()->{
            alergenService.removeAlergenFromUser(updateAlergenListForUserDTO);
        });
        //then
        Assertions.assertEquals("Użytkownik nie ma takiego alergenu, nie mozesz go usunac",thrown.getMessage());
    }


    @Test
    void setAlergenToProduct_should_set() {
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("shouldBeReturned"));
        Alergen alergen2= alergenRepository.save(new Alergen("shouldntBeReturned"));
        Product product= productRepository.save(new Product("product",10L,10L,10L,10L));
        UpdateAlergenListForProductDTO updateAlergenListForProductDTO= new UpdateAlergenListForProductDTO(product.getId(), alergen1.getAlergenId());
        //when
        Product updatedProduct= alergenService.setAlergenToProduct(updateAlergenListForProductDTO);
        //then
        Assertions.assertFalse(updatedProduct.getAlergens().isEmpty());
        Assertions.assertEquals(updatedProduct.getAlergens().get(0).getName(),alergen1.getName());
        Assertions.assertNotEquals(updatedProduct.getAlergens().get(0).getName(),alergen2.getName());
    }

    @Test
    void setAlergenToProduct_should_throw_ProductNotExistException(){
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("alergen"));
        UpdateAlergenListForProductDTO updateAlergenListForProductDTO= new UpdateAlergenListForProductDTO(1L, alergen1.getAlergenId());
        //when
        Exception thrown=Assertions.assertThrows(ProductNotExistException.class,()->{
            alergenService.setAlergenToProduct(updateAlergenListForProductDTO);
        });
        //then
        Assertions.assertEquals("Produkt nie istnieje",thrown.getMessage());
    }

    @Test
    void  setAlergenToProduct_should_throw_AlergenNotExistException(){
        //given
        Product product= productRepository.save(new Product("product",10L,10L,10L,10L));
        UpdateAlergenListForProductDTO updateAlergenListForProductDTO= new UpdateAlergenListForProductDTO(product.getId(), 1L);

        //when
        Exception thrown=Assertions.assertThrows(AlergenNotExistException.class,()->{
            alergenService.setAlergenToProduct(updateAlergenListForProductDTO);
        });
        //then
        Assertions.assertEquals("Alergen nie istnieje",thrown.getMessage());
    }

    @Test
    void setAlergenToProduct_should_throw_ProductAlreadyHaveThisAlergenSavedException(){
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("alergen"));
        Product product= productRepository.save(new Product("product",10L,10L,10L,10L));
        UpdateAlergenListForProductDTO updateAlergenListForProductDTO= new UpdateAlergenListForProductDTO(product.getId(), alergen1.getAlergenId());
        alergenService.setAlergenToProduct(updateAlergenListForProductDTO);
        //when
        Exception thrown=Assertions.assertThrows(ProductAlreadyHaveThisAlergenSavedException.class,()->{
            alergenService.setAlergenToProduct(updateAlergenListForProductDTO);
        });
        //then
        Assertions.assertEquals("Ten alergen jest już przypisany do tego produktu",thrown.getMessage());
    }


    @Test
    void removeAlergenFromProduct_should_remove() {
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("shouldBeReturned"));
        Alergen alergen2= alergenRepository.save(new Alergen("shouldntBeReturned"));
        Product product= productRepository.save(new Product("product",10L,10L,10L,10L));
        UpdateAlergenListForProductDTO updateAlergenListForProductDTO= new UpdateAlergenListForProductDTO(product.getId(), alergen1.getAlergenId());
        alergenService.setAlergenToProduct(updateAlergenListForProductDTO);
        updateAlergenListForProductDTO.setAlergenId(alergen2.getAlergenId());
        alergenService.setAlergenToProduct(updateAlergenListForProductDTO);
        //when
        Product result= alergenService.removeAlergenFromProduct(updateAlergenListForProductDTO);
        //then
        Assertions.assertEquals(1,result.getAlergens().size());
        Assertions.assertEquals(alergen1.getName(),result.getAlergens().get(0).getName());
        Assertions.assertNotEquals(alergen2.getName(),result.getAlergens().get(0).getName());
    }


    @Test
    void removeAlergenFromProduct_should_throw_ProductNotExistException(){
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("alergen"));
        UpdateAlergenListForProductDTO updateAlergenListForProductDTO= new UpdateAlergenListForProductDTO(1L, alergen1.getAlergenId());
        //when
        Exception thrown=Assertions.assertThrows(ProductNotExistException.class,()->{
            alergenService.removeAlergenFromProduct(updateAlergenListForProductDTO);
        });
        //then
        Assertions.assertEquals("Produkt nie istnieje",thrown.getMessage());
    }

    @Test
    void removeAlergenFromProduct_should_throw_AlergenNotExistException(){
        //given
        Product product= productRepository.save(new Product("product",10L,10L,10L,10L));
        UpdateAlergenListForProductDTO updateAlergenListForProductDTO= new UpdateAlergenListForProductDTO(product.getId(), 1L);

        //when
        Exception thrown=Assertions.assertThrows(AlergenNotExistException.class,()->{
            alergenService.removeAlergenFromProduct(updateAlergenListForProductDTO);
        });
        //then
        Assertions.assertEquals("Alergen nie istnieje",thrown.getMessage());
    }

    @Test
    void removeAlergenFromProduct_should_throw_UserDoesntHaveThisAlergenSavedException(){
        //given
        Product product= productRepository.save(new Product("product",10L,10L,10L,10L));
        Alergen alergen1= alergenRepository.save(new Alergen("alergen"));
        UpdateAlergenListForProductDTO updateAlergenListForProductDTO= new UpdateAlergenListForProductDTO(product.getId(),alergen1.getAlergenId());

        //when
        Exception thrown=Assertions.assertThrows(UserDoesntHaveThisAlergenSavedException.class,()->{
            alergenService.removeAlergenFromProduct(updateAlergenListForProductDTO);
        });
        //then
        Assertions.assertEquals("Użytkownik nie ma takiego alergenu, nie mozesz go usunac",thrown.getMessage());
    }

}




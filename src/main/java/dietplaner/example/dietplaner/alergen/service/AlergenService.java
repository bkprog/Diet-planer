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
import dietplaner.example.dietplaner.user.Exceptions.UserNotExistException;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AlergenService {

    private final AlergenRepository alergenRepository;
    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private void validateAlergenWithAlergenName(String alergenName) {
        if(alergenRepository.getAlergenByName(alergenName).isPresent()){
            throw new AlergenAlreadyExistException();
        }

    }


    public Alergen addAlergen(AlergenDTO alergenDTO) {
        validateAlergenWithAlergenName(alergenDTO.getName());
        return alergenRepository.save(Alergen.of(alergenDTO));
    }

    public List<Alergen> getAll() {
        return alergenRepository.findAll();
    }

    public List<Alergen> getAlergensOfUser(Long userId) {
        DefaultUser user = userRepository.findDefaultUserByUserId(userId).stream().findFirst().orElseThrow(UserNotExistException::new);
        return user.getAlergens();
    }

    public DefaultUser setAlergenOfUser(UpdateAlergenListForUserDTO updateAlergenListForUserDTO) {

        DefaultUser user = userRepository.findDefaultUserByUserId(updateAlergenListForUserDTO.getUserId()).stream().findFirst().orElseThrow(UserNotExistException::new);
        List<Alergen> alergensOfUser = user.getAlergens();
        Alergen alergen = alergenRepository.getAlergenByAlergenId(updateAlergenListForUserDTO.getAlergenId()).stream().findFirst().orElseThrow(AlergenNotExistException::new);

        if (alergensOfUser.contains(alergen)) {
            throw new UserAlreadyHaveThisAlergenSavedException();
        } else {
            alergensOfUser.add(alergen);
            user.setAlergens(alergensOfUser);
            return userRepository.save(user);
        }
    }

    public DefaultUser removeAlergenFromUser(UpdateAlergenListForUserDTO updateAlergenListForUserDTO) {

        DefaultUser user = userRepository.findDefaultUserByUserId(updateAlergenListForUserDTO.getUserId()).stream().findFirst().orElseThrow(UserNotExistException::new);
        List<Alergen> alergensOfUser = user.getAlergens();
        Alergen alergen = alergenRepository.getAlergenByAlergenId(updateAlergenListForUserDTO.getAlergenId()).stream().findFirst().orElseThrow(AlergenNotExistException::new);

        if (alergensOfUser.contains(alergen)) {
            alergensOfUser.remove(alergen);
            user.setAlergens(alergensOfUser);
            return userRepository.save(user);
        } else {
            throw new UserDoesntHaveThisAlergenSavedException();
        }
    }

    public Product setAlergenToProduct(UpdateAlergenListForProductDTO updateAlergenListForProductDTO) {
        Product product = productRepository.findById(updateAlergenListForProductDTO.getProductId()).stream().findFirst().orElseThrow(ProductNotExistException::new);
        List<Alergen> alergensOfProduct = product.getAlergens();
        Alergen alergen = alergenRepository.getAlergenByAlergenId(updateAlergenListForProductDTO.getAlergenId()).stream().findFirst().orElseThrow(AlergenNotExistException::new);

        if (alergensOfProduct.contains(alergen)) {
            throw new ProductAlreadyHaveThisAlergenSavedException();
        } else {
            alergensOfProduct.add(alergen);
            product.setAlergens(alergensOfProduct);
            return productRepository.save(product);
        }

    }

    public Product removeAlergenFromProduct(UpdateAlergenListForProductDTO updateAlergenListForProductDTO) {
        Product product = productRepository.findById(updateAlergenListForProductDTO.getProductId()).stream().findFirst().orElseThrow(ProductNotExistException::new);
        List<Alergen> alergensOfProduct = product.getAlergens();
        Alergen alergen = alergenRepository.getAlergenByAlergenId(updateAlergenListForProductDTO.getAlergenId()).stream().findFirst().orElseThrow(AlergenNotExistException::new);

        if (alergensOfProduct.contains(alergen)) {
            alergensOfProduct.remove(alergen);
            product.setAlergens(alergensOfProduct);
            return productRepository.save(product);
        }
        else{
            throw new UserDoesntHaveThisAlergenSavedException();
        }
    }
}




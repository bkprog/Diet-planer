package dietplaner.example.dietplaner.alergen.service;

import dietplaner.example.dietplaner.alergen.entity.Alergen;
import dietplaner.example.dietplaner.alergen.exceptions.AlergenAlreadyExistException;
import dietplaner.example.dietplaner.alergen.exceptions.AlergenNotExistException;
import dietplaner.example.dietplaner.alergen.exceptions.UserAlreadyHaveThisAlergenSavedException;
import dietplaner.example.dietplaner.alergen.exceptions.UserDoesntHaveThisAlergenSavedException;
import dietplaner.example.dietplaner.alergen.models.AlergenDTO;
import dietplaner.example.dietplaner.alergen.models.UpdateAlergenListDTO;
import dietplaner.example.dietplaner.alergen.repository.AlergenRepository;
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

    private void validateAlergenWithAlergenName(String alergenName){
        alergenRepository.getAlergenByName(alergenName).stream().findFirst().orElseThrow(AlergenAlreadyExistException::new);
    }


    public Alergen addAlergen(AlergenDTO alergenDTO){
        validateAlergenWithAlergenName(alergenDTO.getName());
        return alergenRepository.save(Alergen.of(alergenDTO));
    }

    public List<Alergen> getAll(){
        return alergenRepository.findAll();
    }

    public List<Alergen> getAlergensOfUser(Long userId){
        DefaultUser user=userRepository.findDefaultUserByUserId(userId).stream().findFirst().orElseThrow(UserNotExistException::new);
        return user.getAlergens();
    }

    public DefaultUser setAlergenOfUser(UpdateAlergenListDTO updateAlergenListDTO){

        DefaultUser user= userRepository.findDefaultUserByUserId(updateAlergenListDTO.getUserId()).stream().findFirst().orElseThrow(UserNotExistException::new);
        List<Alergen> alergensOfUser= user.getAlergens();
        Alergen alergen= alergenRepository.getAlergenByAlergenId(updateAlergenListDTO.getAlergenId()).stream().findFirst().orElseThrow(AlergenNotExistException::new);

        if(alergensOfUser.contains(alergen)){
            throw new UserAlreadyHaveThisAlergenSavedException();
        }
        else{
            alergensOfUser.add(alergen);
            user.setAlergens(alergensOfUser);
            return userRepository.save(user);
        }
    }

    public DefaultUser removeAlergenFromUser(UpdateAlergenListDTO updateAlergenListDTO){

        DefaultUser user= userRepository.findDefaultUserByUserId(updateAlergenListDTO.getUserId()).stream().findFirst().orElseThrow(UserNotExistException::new);
        List<Alergen> alergensOfUser= user.getAlergens();
        Alergen alergen= alergenRepository.getAlergenByAlergenId(updateAlergenListDTO.getAlergenId()).stream().findFirst().orElseThrow(AlergenNotExistException::new);

        if(alergensOfUser.contains(alergen)){
            alergensOfUser.remove(alergen);
            user.setAlergens(alergensOfUser);
            return userRepository.save(user);
        }
        else{
            throw new UserDoesntHaveThisAlergenSavedException();
        }
    }


}

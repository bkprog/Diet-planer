package dietplaner.example.dietplaner.alergen.controller;

import dietplaner.example.dietplaner.alergen.entity.Alergen;
import dietplaner.example.dietplaner.alergen.models.AlergenDTO;
import dietplaner.example.dietplaner.alergen.models.UpdateAlergenListForProductDTO;
import dietplaner.example.dietplaner.alergen.models.UpdateAlergenListForUserDTO;
import dietplaner.example.dietplaner.alergen.service.AlergenService;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/alergen")
public class AlergenController {

    private final AlergenService alergenService;

    @PostMapping("/add")
    public Alergen addAlergen(@RequestBody AlergenDTO alergenDTO){
        return alergenService.addAlergen(alergenDTO);
    }

    @GetMapping("/getall")
    public List<Alergen> getAllAlergens(){
        return alergenService.getAll();
    }

    @GetMapping("/getAlergensOfUser")
    public List<Alergen> getAlergenOfUser(@RequestBody Long userId){
        return  alergenService.getAlergensOfUser(userId);
    }

    @PatchMapping("/setAlergenToUser")
    public DefaultUser setAlergenToUser(@RequestBody UpdateAlergenListForUserDTO updateAlergenListForUserDTO){
        return alergenService.setAlergenOfUser(updateAlergenListForUserDTO);
    }

    @PatchMapping("/removeAlergenFromUser")
    public DefaultUser removeAlergenFromUser(@RequestBody UpdateAlergenListForUserDTO updateAlergenListForUserDTO){
        return alergenService.removeAlergenFromUser(updateAlergenListForUserDTO);
    }

    @PatchMapping("/setAlergenToProduct")
    public Product setAlergenToProduct(@RequestBody UpdateAlergenListForProductDTO updateAlergenListForProductDTO){
        return alergenService.setAlergenToProduct(updateAlergenListForProductDTO);
    }

    @PatchMapping("/removeAlergenFromProduct")
    public Product removeAlergenFromProduct(@RequestBody UpdateAlergenListForProductDTO updateAlergenListForProductDTO){
        return alergenService.removeAlergenFromProduct(updateAlergenListForProductDTO);
    }
}

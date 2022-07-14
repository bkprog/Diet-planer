package dietplaner.example.dietplaner.alergen.controller;

import dietplaner.example.dietplaner.alergen.entity.Alergen;
import dietplaner.example.dietplaner.alergen.models.AlergenDTO;
import dietplaner.example.dietplaner.alergen.models.UpdateAlergenListDTO;
import dietplaner.example.dietplaner.alergen.service.AlergenService;
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
    public DefaultUser setAlergenToUser(@RequestBody UpdateAlergenListDTO updateAlergenListDTO){
        return alergenService.setAlergenOfUser(updateAlergenListDTO);
    }

    @PatchMapping("/removeAlergenFromUser")
    public DefaultUser removeAlergenFromUser(@RequestBody UpdateAlergenListDTO updateAlergenListDTO){
        return alergenService.removeAlergenFromUser(updateAlergenListDTO);
    }



}

package dietplaner.example.dietplaner.alergen.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import dietplaner.example.dietplaner.alergen.models.AlergenDTO;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Alergen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alergenId;

    @Column
    private String name;

    @JsonBackReference(value = "AlergenToUser")
    @ManyToMany(mappedBy = "alergens")
    private List<DefaultUser> users;

   @JsonBackReference(value = "AlergenToProduct")
    @ManyToMany(mappedBy = "alergens")
    private List<Product> products;

    public static Alergen of(AlergenDTO alergenDTO){
        Alergen alergen= new Alergen();
        alergen.setName(alergenDTO.getName());
        return alergen;
    }

    public Alergen(String name){
        this.name=name;
    }
}

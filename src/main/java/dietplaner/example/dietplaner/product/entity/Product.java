package dietplaner.example.dietplaner.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dietplaner.example.dietplaner.alergen.entity.Alergen;
import dietplaner.example.dietplaner.product.models.ProductDTO;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column
    private Long kcal;

    @Column
    private Long carbs;

    @Column
    private Long fat;

    @Column
    private Long protein;

    @JsonBackReference(value = "ProductToRecipe")
    @ManyToMany
    @JoinTable(
            name="ProductToRecipe",
            joinColumns = @JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Recipe> recipes;

    @JsonManagedReference(value = "ProductToAlergen")
    @ManyToMany
    @JoinTable(
            name="alergensToProduct",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "alergenId")
    )
    private List<Alergen> alergens;

    public Product(Long kcal, Long carbs, Long fat, Long protein) {
        this.kcal = kcal;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }

    public Product(String productName,Long kcal, Long carbs, Long fat, Long protein) {
        this.productName=productName;
        this.kcal = kcal;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }

    public Product(String productName,Long kcal, Long carbs, Long fat, Long protein, List<Alergen> alergens){
        this.productName=productName;
        this.kcal = kcal;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
        this.alergens=alergens;
    }

    public static Product of(ProductDTO productDTO){
        Product product= new Product();
        product.setProductName(productDTO.getProductName());
        product.setKcal(productDTO.getKcal());
        product.setCarbs(productDTO.getCarbs());
        product.setProtein(productDTO.getProtein());
        product.setFat(productDTO.getFat());
        return product;
    }



}

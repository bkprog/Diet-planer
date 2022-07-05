package dietplaner.example.dietplaner.product.entity;

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

    @ManyToMany
    @JoinTable(
            name="ProductToRecipe",
            joinColumns = @JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Recipe> recipes;


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

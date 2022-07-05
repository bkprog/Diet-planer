package dietplaner.example.dietplaner.product.models;


import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private String productName;
    private Long kcal;
    private Long carbs;
    private Long fat;
    private Long protein;
}

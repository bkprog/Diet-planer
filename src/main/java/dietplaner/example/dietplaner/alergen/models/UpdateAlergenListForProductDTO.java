package dietplaner.example.dietplaner.alergen.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAlergenListForProductDTO {
    private Long productId;
    private Long alergenId;
}

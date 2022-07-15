package dietplaner.example.dietplaner.alergen.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateAlergenListForUserDTO {

    private Long userId;
    private Long alergenId;

}

package dietplaner.example.dietplaner.alergen.entity;


import lombok.*;

import javax.persistence.*;


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
}

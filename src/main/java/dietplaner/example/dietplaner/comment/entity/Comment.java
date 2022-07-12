package dietplaner.example.dietplaner.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private DefaultUser user;



}

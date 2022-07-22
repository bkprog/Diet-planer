package dietplaner.example.dietplaner.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference(value = "CommentToRecipe")
    @ManyToOne
    private Recipe recipe;

    @JsonBackReference(value = "CommentToUser")
    @ManyToOne
    private DefaultUser user;



}

package dietplaner.example.dietplaner.comment.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    String text;
    Long recipeId;
    Long userId;

}

package dietplaner.example.dietplaner.comment.service;

import dietplaner.example.dietplaner.comment.entity.Comment;
import dietplaner.example.dietplaner.comment.exceptions.CommentNotExistException;
import dietplaner.example.dietplaner.comment.models.CommentDTO;
import dietplaner.example.dietplaner.comment.repository.CommentRepository;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.service.RecipeService;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CommentService {

    private final UserService userService;
    private final RecipeService recipeService;

    private final CommentRepository commentRepository;

    private void validateCommentById(Long id){
        commentRepository.findCommentById(id).stream().findFirst().orElseThrow(CommentNotExistException::new);
    }
    public Comment addComment(CommentDTO commentDTO){
        DefaultUser user= userService.findUserById(commentDTO.getUserId());
        Recipe recipe= recipeService.getRecipeById(commentDTO.getRecipeId());
        Comment comment= new Comment();

        comment.setText(commentDTO.getText());
        comment.setRecipe(recipe);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteCommentById(Long id){
        validateCommentById(id);
        commentRepository.deleteById(id);
    }
}

package dietplaner.example.dietplaner.comment.service;

import dietplaner.example.dietplaner.comment.entity.Comment;
import dietplaner.example.dietplaner.comment.exceptions.CommentNotExistException;
import dietplaner.example.dietplaner.comment.models.CommentDTO;
import dietplaner.example.dietplaner.comment.repository.CommentRepository;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    @AfterEach
    void tearDown(){
        commentRepository.deleteAll();
        userRepository.deleteAll();
        recipeRepository.deleteAll();
    }

    @Test
    void addComment_should_add() {
        //given
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));
        Recipe recipe= recipeRepository.save(new Recipe("name","description"));
        CommentDTO commentDTO= new CommentDTO("text",recipe.getRecipeId(),user.getUserId());
        //when
        Comment result= commentService.addComment(commentDTO);
        //then
        Assertions.assertEquals(commentDTO.getText(),result.getText());
    }

    @Test
    void deleteCommentById_should_delete() {
        //given
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));
        Recipe recipe= recipeRepository.save(new Recipe("name","description"));
        CommentDTO commentDTO= new CommentDTO("text",recipe.getRecipeId(),user.getUserId());
        Comment comment=commentService.addComment(commentDTO);
        //when
        commentService.deleteCommentById(comment.getId());
        //then
        List<Comment> comments=commentRepository.findAll();
        Assertions.assertTrue(comments.isEmpty());
    }

    @Test
    void deleteCommentById_should_throw_CommentNotExistException(){
        //given

        //when
        Exception thrown=Assertions.assertThrows(CommentNotExistException.class,()->{
            commentService.deleteCommentById(1L);
        });
        //then
        Assertions.assertEquals("Taki komentarz nie istnieje",thrown.getMessage());
    }
}
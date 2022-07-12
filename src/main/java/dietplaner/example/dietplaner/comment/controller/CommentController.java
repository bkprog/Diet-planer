package dietplaner.example.dietplaner.comment.controller;

import dietplaner.example.dietplaner.comment.entity.Comment;
import dietplaner.example.dietplaner.comment.models.CommentDTO;
import dietplaner.example.dietplaner.comment.repository.CommentRepository;
import dietplaner.example.dietplaner.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping("/api/comment/add")
    public Comment addComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @DeleteMapping("/api/comment/delete")
   public void deleteComment(@RequestBody Long id){
        commentService.deleteCommentById(id);
   }

   @GetMapping("/api/comment/getall")
   public List<Comment> getAllComments(){
        return commentRepository.findAll();
   }


}
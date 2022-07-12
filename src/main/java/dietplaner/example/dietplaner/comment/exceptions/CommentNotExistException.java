package dietplaner.example.dietplaner.comment.exceptions;

public class CommentNotExistException extends RuntimeException{

    public CommentNotExistException(){
        super("Taki komentarz nie istnieje");
    }
}

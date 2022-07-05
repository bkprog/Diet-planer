package dietplaner.example.dietplaner.product.exceptions;

public class ProductAlreadyExistException extends RuntimeException {


        public ProductAlreadyExistException(){super("Wprowadzona nazwa produktu jest już zajęta, wybierz inny");}

}

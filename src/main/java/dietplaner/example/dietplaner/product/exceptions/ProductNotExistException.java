package dietplaner.example.dietplaner.product.exceptions;

public class ProductNotExistException extends RuntimeException{
    public ProductNotExistException(){super("Produkt nie istnieje");}
}

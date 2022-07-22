package dietplaner.example.dietplaner.dailyconsumption.exceptions;



public class DailyConsumptionAlreadyExistException extends RuntimeException{

    public DailyConsumptionAlreadyExistException(){super("Ten użytkownik ma już zestawienie z tego dnia");}
}

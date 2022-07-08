package dietplaner.example.dietplaner.dailyconsumption.exceptions;

public class DailyConsumptionNotExistException extends RuntimeException{

    public DailyConsumptionNotExistException(){
        super("Zestawienie dzienne nie istnieje");
    }

}

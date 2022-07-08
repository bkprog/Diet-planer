package dietplaner.example.dietplaner.dailyconsumption.exceptions;

public class UserDoesNotHaveAnyDailyConsumptionsException extends RuntimeException{

    public UserDoesNotHaveAnyDailyConsumptionsException(){
        super("Użytkownik nie posiada żadnych zestawień dziennych");
    }
}

package dietplaner.example.dietplaner.utils;


import org.springframework.security.crypto.bcrypt.BCrypt;

public final class Passwordhash {

private Passwordhash(){}


    public static String passwordHash(String password){
        return BCrypt.hashpw(password,"$2a$10$D4Q.Ig8qXDsbtRGkuvzwf.fmd");
    }




}

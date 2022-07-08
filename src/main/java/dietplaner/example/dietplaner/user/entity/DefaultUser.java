package dietplaner.example.dietplaner.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.user.models.UserRegisterDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DefaultUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String login;

    @Column
    private String password;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "defaultUser")
    List<DailyConsumption> dailyConsumptions;


    public static DefaultUser of(UserRegisterDTO userRegisterDTO){
        DefaultUser user = new DefaultUser();

        user.setLogin(userRegisterDTO.getLogin());
        user.setPassword(userRegisterDTO.getPassword());
        user.setName(userRegisterDTO.getName());
        user.setSurname(userRegisterDTO.getSurname());
        user.setEmail(userRegisterDTO.getEmail());
        return user;
    }


}

package dietplaner.example.dietplaner.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dietplaner.example.dietplaner.alergen.entity.Alergen;
import dietplaner.example.dietplaner.comment.entity.Comment;
import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.user.models.UserRegisterDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    @JsonManagedReference(value = "userToDaily")
    @OneToMany(mappedBy = "defaultUser")
    List<DailyConsumption> dailyConsumptions;


    @JsonManagedReference(value = "userToComment")
    @OneToMany(
            mappedBy = "user"
    )
    private List<Comment> comments;

    @JsonManagedReference(value = "UserToAlergen")
    @ManyToMany
    @JoinTable(
            name="alergensToUser",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "alergenId")
    )
    private List<Alergen> alergens;

    public static DefaultUser of(UserRegisterDTO userRegisterDTO){
        DefaultUser user = new DefaultUser();

        user.setLogin(userRegisterDTO.getLogin());
        user.setPassword(userRegisterDTO.getPassword());
        user.setName(userRegisterDTO.getName());
        user.setSurname(userRegisterDTO.getSurname());
        user.setEmail(userRegisterDTO.getEmail());
        return user;
    }

    public DefaultUser(String login,String password,String name, String surname,String email){
        this.login=login;
        this.password=password;
        this.name=name;
        this.surname=surname;
        this.email=email;
    }

    public DefaultUser(String login,String password,String name, String surname,String email,List<Alergen> alergens){
        this.login=login;
        this.password=password;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.alergens=alergens;
    }

}

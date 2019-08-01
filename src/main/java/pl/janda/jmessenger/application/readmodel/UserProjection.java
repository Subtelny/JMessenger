package pl.janda.jmessenger.application.readmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProjection {

    @Id
    private String userId;

    private String email;

    private String nick;

    private String password;

}

package pl.janda.jmessenger.domain.model.user;

import lombok.AllArgsConstructor;
import pl.janda.jmessenger.domain.model.EntityId;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@Embeddable
public class UserId extends EntityId implements Serializable{

    public UserId(String id) {
        super(id);
    }

    @Override
    protected int hashValue() {
        return 46;
    }
}

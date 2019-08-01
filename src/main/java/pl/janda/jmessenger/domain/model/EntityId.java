package pl.janda.jmessenger.domain.model;

import org.apache.commons.lang3.Validate;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class EntityId implements Serializable {

    private String id;

    protected EntityId() {
        super();
    }

    public EntityId(String id) {
        this();
        setId(id);
    }

    public void setId(String id) {
        Validate.notNull(id, "Id should not be null");

        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + id + "]";
    }

    @Override
    public int hashCode() {
        return hashValue() + id.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;

        EntityId eObject = (EntityId) object;
        return getId().equals(eObject.id);
    }

    public String getId() {
        return id;
    }

    protected abstract int hashValue();
}

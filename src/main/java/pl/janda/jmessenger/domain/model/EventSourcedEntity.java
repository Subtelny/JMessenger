package pl.janda.jmessenger.domain.model;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Setter
public abstract class EventSourcedEntity implements Serializable {

    private List<DomainEvent> newEvents = new ArrayList<>();
    private int rawVersion;

    protected EventSourcedEntity() {

    }

    protected EventSourcedEntity(List<DomainEvent> eventStream, int streamVersion) {
        Preconditions.checkNotNull(eventStream);

        eventStream.forEach(this::entityApplyEvent);
        setRawVersion(streamVersion);
    }

    protected void applyEvent(DomainEvent event) {
        newEvents.add(event);
        entityApplyEvent(event);
    }

    void entityApplyEvent(DomainEvent event) {
        Class<? extends EventSourcedEntity> aggregateClass = this.getClass();
        Class<? extends DomainEvent> eventClass = event.getClass();

        try {
            Method method = aggregateClass.getDeclaredMethod("apply", eventClass);
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Method apply (" + eventClass.getSimpleName() + ") not found in " + aggregateClass.getSimpleName() + ". Cause: " + e.getMessage(), e);

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Method apply (" + eventClass.getSimpleName() + ") failed because illegal access " + aggregateClass.getSimpleName() + ". Cause: " + e.getMessage(), e);

        } catch (InvocationTargetException e) {
            throw new RuntimeException("Method apply (" + eventClass.getSimpleName() + ") failed " + aggregateClass.getSimpleName() + ". Cause: " + e.getMessage(), e);

        }
    }

    public List<DomainEvent> getNewEvents() {
        return Collections.unmodifiableList(newEvents);
    }

    public int getVersion() {
        return rawVersion + 1;
    }

}

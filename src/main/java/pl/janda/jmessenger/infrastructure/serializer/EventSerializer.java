package pl.janda.jmessenger.infrastructure.serializer;

import com.google.gson.Gson;
import pl.janda.jmessenger.domain.model.DomainEvent;

public final class EventSerializer {

    private static EventSerializer instance;

    private Gson gson;

    public EventSerializer() {
        gson = new Gson();
    }

    public static EventSerializer instance() {
        if(instance == null)
            instance = new EventSerializer();
        return instance;
    }

    public String serialize(DomainEvent event) {
        return gson.toJson(event);
    }

    public <T extends DomainEvent> T deserialize(String serialization, final Class<T> type) {
        return gson.fromJson(serialization, type);
    }

}

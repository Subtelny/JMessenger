package pl.janda.jmessenger.domain.model.room;

public interface Messages {

    Message withId(MessageId messageId);

    void save(Message message);

}

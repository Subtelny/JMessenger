package pl.janda.jmessenger.domain.model.room;

public interface Rooms {

    Room withId(RoomId roomId);

    void save(Room room);

}

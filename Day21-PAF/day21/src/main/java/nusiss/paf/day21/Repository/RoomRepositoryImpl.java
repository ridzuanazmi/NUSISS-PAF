package nusiss.paf.day21.Repository;

import java.util.List;

import nusiss.paf.day21.Model.Room;

public interface RoomRepositoryImpl {
    
    int count();

    Boolean save(Room room);

    List<Room> getAll();

    Room getRoomById(Integer id);

    int update(Room room);

    int deleteById(Integer id);
}

package nusiss.paf.day21.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day21.Model.Room;
import nusiss.paf.day21.Repository.RoomRepository;

@Service
public class RoomService {
    
    @Autowired
    RoomRepository roomRepo;

   
    public int count() {
        return roomRepo.count();
    }

    public Boolean save(Room room) {
        return roomRepo.save(room);
    }

    
    public List<Room> getAll() {
        return roomRepo.getAll();
    }

    
    public Room getRoomById(Integer id) {
        return roomRepo.getRoomById(id);
    }

    
    public int update(Room room) {
       return roomRepo.update(room);
    }

    
    public int deleteById(Integer id) {
        return roomRepo.deleteById(id);
    }
}

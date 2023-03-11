package nusiss.paf.day21.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day21.Model.Room;
import nusiss.paf.day21.Service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    RoomService roomSrvc;
    
    @GetMapping("/count")
    public ResponseEntity<Integer> getRoomCount() {

        Integer roomCount = roomSrvc.count();

        return new ResponseEntity<Integer>(roomCount, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {

        List<Room> roomList = roomSrvc.getAll();

        if(!roomList.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(roomList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> saveRoom(@RequestBody(required = false) Room room) {

        Boolean created = roomSrvc.save(room);

        if(created) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(created, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}

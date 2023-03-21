package nusiss.paf.day22revision.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day22revision.Model.Rsvp;
import nusiss.paf.day22revision.Service.RsvpService;

@RestController
public class RsvpRestController {
    
    @Autowired
    RsvpService rsvpSrvc;

    @GetMapping("/api/rsvps")
    public ResponseEntity<?> getAllRsvp() {

        List<Rsvp> rsvpList = rsvpSrvc.getAllRsvp();

        if (!rsvpList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(rsvpList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("RSVP list not found");
        }
    }

    @GetMapping("/api/rsvp")
    public ResponseEntity<?> getRsvpByName(@RequestParam("q") String fullName) {

        List<Rsvp> rsvpList = rsvpSrvc.getRsvpByName(fullName);

        if (!rsvpList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(rsvpList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid name entered");
        }
    }

    @PostMapping("/api/rsvp")
    public ResponseEntity<?> createRsvp(@RequestBody Rsvp rsvp) {

        Boolean isCreated = rsvpSrvc.createRsvp1(rsvp);

        if (isCreated) {
            return ResponseEntity.ok()
                    .body("Successfully created your rsvp");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("RSVP could not be created");
        }
    }
    
    @PutMapping("/api/rsvp")
    public ResponseEntity<?> updateRsvp(@RequestBody Rsvp rsvpForm) {

        Boolean isUpdated = rsvpSrvc.updateRsvp1(rsvpForm);

        if (isUpdated) {
            return ResponseEntity.ok()
                    .body("RSVP successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("RSVP could not be updated");
        }
    }

    @GetMapping("/api/rsvps/count")
    public ResponseEntity<?> getRsvpCount() {

        List<Rsvp> rsvpList = rsvpSrvc.getAllRsvp();

        if (!rsvpList.isEmpty()) {
            return ResponseEntity.ok()
                    .body("The number of people who RSVP is %d".formatted(rsvpList.size()));
        } else {
            return ResponseEntity.badRequest()
                    .body("Could not count RSVP record");
        }
    }

    @DeleteMapping("/api/rsvp/{id}")
    public ResponseEntity<?> deleteRsvpById(@PathVariable("id") Integer id) {

        Boolean isDeleted = rsvpSrvc.deleteRsvpById(id);

        if (isDeleted) {
            return ResponseEntity.ok()
                    .body("RSVP record successfully deleted");
        } else {
            return ResponseEntity.badRequest()
                    .body("RSVP record not deleted");
        }
    }
}

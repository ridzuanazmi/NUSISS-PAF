package nusiss.paf.revisionday22workshop.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nusiss.paf.revisionday22workshop.Model.Rsvp;
import nusiss.paf.revisionday22workshop.Service.RsvpService;

@Controller
public class RsvpController {

    @Autowired
    private RsvpService rsvpSrvc;

    // GET /api/rsvps
    // Accept: application/json
    @GetMapping(path = "/api/rsvps")
    @ResponseBody
    public ResponseEntity<?> getAllRsvp() {

        List<Rsvp> rsvpList = rsvpSrvc.getAllRsvp();
        System.out.printf(">>>> RSVPLIST: %s\n", rsvpList);

        if (!rsvpList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(rsvpList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not get RSVP list");
        }
    }

    // GET /api/rsvp?q=fred
    // Accept: application/json
    @GetMapping(path = "/api/rsvp")
    @ResponseBody
    public ResponseEntity<?> getRsvpByName(@RequestParam("q") String name) {

        Optional<Rsvp> rsvp = rsvpSrvc.getRsvpByName(name);
        System.out.printf(">>>> RSVP: %s\n", rsvp.toString());

        if (rsvp.isPresent()) {
            return ResponseEntity.ok()
                    .body(rsvp);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rsvp with name %s does not exist".formatted(name));
        }
    }

    // Get /rsvpform
    // Rsvp form page for the POST method. To test out url form encoded
    @GetMapping(path = "/rsvpform")
    public String rsvpForm() {
        return "view1";
    }

    // POST /api/rsvp
    // Content-Type: application/x-www-form-urlencoded
    // Accept: application/json
    // This method does not use thymeleaf to bind the Rsvp object into the form
    // Also does not perform the overwrite function
    @PostMapping(path = "/api/rsvp", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> createRsvp(@ModelAttribute Rsvp rsvpInsert) {

        Rsvp rsvp = rsvpSrvc.createRsvpUtil(rsvpInsert);

        System.out.printf("POST api/rsvp: %s\n", rsvp.toString());
        Boolean createRsvp = rsvpSrvc.createRsvp(rsvp);

        if (createRsvp) {
            return ResponseEntity.status(201)
                    .body(rsvp);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Could not create RSVP record");
        }
    }

    // PUT /api/rsvp/fred@gmail.com
    // Content-Type: application/x-www-form-urlencoded
    // Accept: application/json
    @PutMapping(path = "/api/rsvp/{email}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> updateRsvp(
            @PathVariable("email") String email,
            @ModelAttribute Rsvp updatedRsvp) {

        Optional<Rsvp> rsvpOpt = rsvpSrvc.getRsvpByEmail(email);
        Rsvp rsvp = rsvpOpt.orElse(null);

        if (rsvp == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        rsvpSrvc.updateRsvpUtil(rsvp, email, updatedRsvp);
        System.out.printf(">>>> UPDATED RSVP: %s\n", rsvp.toString());

        Boolean isUpdated = rsvpSrvc.updateRsvp(rsvp);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Update successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Update unsuccessful");
        }
    }

    // GET /api/rsvps/count
    // Accept: application/json
    @GetMapping(path = "/api/rsvps/count", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> rsvpCount() {

        List<Rsvp> rsvpList = rsvpSrvc.getAllRsvp();

        if (rsvpList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("RSVP list is empty");
        } else {
            int count = rsvpList.size();
            return ResponseEntity.ok()
                .body("RSVP list has %d entries".formatted(count));
        }
    }

    // DELETE /api/rsvp/{id}
    // Deletes rsvp row via id. Something extra to do for CRUD
    @DeleteMapping(path = "/api/rsvp/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteRsvp(@PathVariable("id") Integer id) {

        Boolean isDeleted = rsvpSrvc.deleteRsvpById(id);

        if (isDeleted) {
            return ResponseEntity.ok()
                .body("RSVP Id: %d successfully deleted".formatted(id));
        } else {
            return ResponseEntity.badRequest()
                .body("RSVP id: %d does not exist".formatted(id));
        }
    }
}

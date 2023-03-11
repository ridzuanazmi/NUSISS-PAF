package nusiss.paf.day22workshop.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day22workshop.Model.ErrorResponse;
import nusiss.paf.day22workshop.Model.Rsvp;
import nusiss.paf.day22workshop.Service.RsvpService;

@RestController
public class RsvpController {

    @Autowired
    private RsvpService rsvpSrvc;

    private Logger logger = Logger.getLogger(Rsvp.class.getName());

    // GET /api/rsvps
    // Accept: application/json
    @GetMapping
    @RequestMapping("/api/rsvps")
    public ResponseEntity<List<Rsvp>> getAll() {

        List<Rsvp> rsvpList = rsvpSrvc.getAll();

        if (!rsvpList.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(rsvpList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    // GET /api/rsvp?q=fred
    // Accept: application/json
    @GetMapping
    @RequestMapping(path = "/api/rsvp/{fullName}")
    public ResponseEntity<?> getRsvpByName(@PathVariable(required = false) String fullName) {

        List<Rsvp> rsvpList = rsvpSrvc.getRsvpByName(fullName);

        if (!rsvpList.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(rsvpList);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("RSVP not found",
                    "No RSVP record was found for the given full name");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
    }

    // Both @RequestParam and @RequestBody works
    @PostMapping(path = "/api/rsvp", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ResponseEntity<?> create(@RequestParam MultiValueMap<String,String> rsvpForm) throws ParseException {

        Rsvp rsvp = new Rsvp();
        // Set the key/value pairs in the table
        rsvp.setFullName(rsvpForm.getFirst("fullName"));
        rsvp.setEmail(rsvpForm.getFirst("email"));
        rsvp.setPhone(rsvpForm.getFirst("phone"));
        String confirmationDate = rsvpForm.getFirst("confirmationDate");

        // Need to convert String into java.sql.Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Creating date format
        java.util.Date parsedDate = dateFormat.parse(confirmationDate); // Parsing date string to java.util.Date need to throw ParseException
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // Converting java.util.Date to java.sql.Date
        rsvp.setConfirmationDate(sqlDate); // set the confirmationDate in Rsvp table/object
        rsvp.setComments(rsvpForm.getFirst("comments"));
        Boolean createRsvp = rsvpSrvc.create(rsvp);

        if (createRsvp) {
            return ResponseEntity.status(201)
                    .body("RSVP record Successfully created");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Could not create RSVP record");
        }
    }

    // @RequestBody doesnt work but @RrequestParam works
    @PutMapping(path = "/api/rsvp/{email}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestParam String email, @RequestParam MultiValueMap<String,String> rsvpForm) throws ParseException {

        Rsvp rsvp = rsvpSrvc.getRsvpByEmail(email);
        logger.info("PUT /rsvp: %s".formatted(rsvp.toString()));

        rsvp.setFullName(rsvpForm.getFirst("fullName"));
        rsvp.setPhone(rsvpForm.getFirst("phone"));
        rsvp.setEmail(rsvpForm.getFirst("email"));
        String confirmationDate = rsvpForm.getFirst("confirmationDate");

        // Need to convert String into java.sql.Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Creating date format
        java.util.Date parsedDate = dateFormat.parse(confirmationDate); // Parsing date string to java.util.Date need to throw ParseException
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // Converting java.util.Date to java.sql.Date
        rsvp.setConfirmationDate(sqlDate); // set the confirmationDate in Rsvp table/object
        rsvp.setComments(rsvpForm.getFirst("comments"));

        String idString = rsvpForm.getFirst("id"); // converts String into int
        int id = Integer.parseInt(idString);
        rsvp.setId(id);         

        Boolean updateRsvp = rsvpSrvc.update(rsvp);

        if (updateRsvp) {
            return ResponseEntity.status(201)
                    .body(rsvp);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Could not create RSVP record");
        }
    }

    // This method does not use the query line of count(*)
    @GetMapping(path = "/api/rsvps/count", produces = "application/json")
    public ResponseEntity<?> countRsvp() {

        List<Rsvp> rsvpList = rsvpSrvc.getAll();

        if (!rsvpList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("The number of records in RSVP is %d".formatted(rsvpList.size()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    // Why when I change to @PostMapping it can work with @requestBody?
    // Why when I use @PutMapping and use @requestParam it works?
    // @PutMapping(path = "/api/rsvp/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<?> update(@RequestParam MultiValueMap<String, String> rsvpForm) throws ParseException {

    //     Rsvp rsvp = new Rsvp();

        // rsvp.setFullName(rsvpForm.getFirst("fullName"));
        // rsvp.setPhone(rsvpForm.getFirst("phone"));
        // rsvp.setEmail(rsvpForm.getFirst("email"));
        // String confirmationDate = rsvpForm.getFirst("confirmationDate");

        // // Need to convert String into java.sql.Date
        // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Creating date format
        // java.util.Date parsedDate = dateFormat.parse(confirmationDate); // Parsing date string to java.util.Date need to throw ParseException
        // java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // Converting java.util.Date to java.sql.Date
        // rsvp.setConfirmationDate(sqlDate); // set the confirmationDate in Rsvp table/object
        // rsvp.setComments(rsvpForm.getFirst("comments"));

        // String idString = rsvpForm.getFirst("id"); // converts String into int
        // int id = Integer.parseInt(idString);
        // rsvp.setId(id);         

        // Boolean updateRsvp = rsvpSrvc.update(rsvp);

        // if (updateRsvp) {
        //     return ResponseEntity.status(201)
        //             .body(rsvp);
        // } else {
        //     return ResponseEntity.status(HttpStatus.CONFLICT)
        //             .body("Could not create RSVP record");
        // }
        
    // }

    // this method works but accepts and consumes application/json
    // @PutMapping(path = "/api/rsvp/update")
    // public ResponseEntity<?> update(@RequestBody Rsvp rsvp) {

    //     Boolean updateRsvp = rsvpSrvc.update(rsvp);

    //     if (updateRsvp) {
    //         return ResponseEntity.status(201)
    //                 .body("RSVP record Successfully created");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                 .body("Could not upadte RSVP record");
    //     }
    // }

    // @PostMapping(path = "/api/rsvp", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    // public ResponseEntity<?> create(@RequestBody MultiValueMap<String, Object> rsvpForm) {

    //     Rsvp rsvp = new Rsvp();

    //     // Modified the type of the MultiValueMap value from Date to Object so that it can accept both String and Date values
    //     // Cast the object into a string
    //     rsvp.setFullName((String) rsvpForm.getFirst("fullName"));
    //     rsvp.setEmail((String) rsvpForm.getFirst("email"));
    //     rsvp.setPhone((String) rsvpForm.getFirst("phone"));

    //     // Code below converts the Object into a date. Dont know if there is a better way to do it
    //     Object confirmationDate = rsvpForm.getFirst("confirmationDate");
    //     if (confirmationDate instanceof String) {
    //         // Convert the String to Date
    //         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    //         try {
    //             java.util.Date date = df.parse((String) confirmationDate);
    //             rsvp.setConfirmationDate(new java.sql.Date(date.getTime()));
    //         } catch (ParseException e) {
    //             // Handle the ParseException
    //         }
    //     } else if (confirmationDate instanceof Date) {
    //         rsvp.setConfirmationDate(new java.sql.Date(((Date) confirmationDate).getTime()));
    //     }
    //     // Same for comments, cast it into a string
    //     rsvp.setComments((String) rsvpForm.getFirst("comments"));

    //     Boolean createRsvp = rsvpSrvc.create(rsvp);

        // if (createRsvp) {
        //     return ResponseEntity.ok()
        //             .body("RSVP record Successfully created");
        // } else {
        //     return ResponseEntity.status(HttpStatus.CONFLICT)
        //             .body("Could not create RSVP record");
        // }
    // }
}

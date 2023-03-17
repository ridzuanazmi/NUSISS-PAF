package nusiss.paf.day24transaction.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day24transaction.Service.BookResvService;
import nusiss.paf.day24transaction.payload.BookResvRequest;

@RestController
@RequestMapping("/api/book/reservations")
public class BookResvController {
    
    @Autowired
    BookResvService bookResvSrvc;

    @PostMapping
    public ResponseEntity<?> reserveBook(@RequestBody BookResvRequest brq) {

        Boolean isResvSuccessful = bookResvSrvc.reserveBook(brq.getBookList(), brq.getFullName());

        if (isResvSuccessful) {
            return ResponseEntity.ok()
                    .body("Book Reservation successful! :)");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Book reservation unsuccessful :(");
        }
    }
}

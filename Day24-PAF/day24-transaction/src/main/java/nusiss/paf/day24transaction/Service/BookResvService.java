package nusiss.paf.day24transaction.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nusiss.paf.day24transaction.Exception.BookException;
import nusiss.paf.day24transaction.Model.Book;
import nusiss.paf.day24transaction.Model.Resv;
import nusiss.paf.day24transaction.Model.ResvDetails;
import nusiss.paf.day24transaction.Repository.BookResvRepo;

@Service
public class BookResvService {

    @Autowired
    BookResvRepo bookResvRepo;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public Boolean reserveBook(List<Book> books, String reservePersonName) {

        Boolean isReservationCompleted = false;

        // Check for book availability by quantity
        Boolean isBookAvailable = false;
        List<Book> bookList = bookResvRepo.findAllBook();

        for (Book book : books) { // Go through the book list that is sent to the API

            // Filter out the book that is sent as a request body to ensure it matches with the book table records
            List<Book> filteredBook = bookList.stream().filter(b -> b.getId().equals(book.getId()) && b.getTitle().equals(book.getTitle()))
                .collect(Collectors.toList());

            // If books available, minus the quantity from the book (requires transaction)
            if (!filteredBook.isEmpty()) {
                if (0 == filteredBook.get(0).getQuantity()) {
                    isBookAvailable = false;
                    break;
                } else {
                    // quantity update marker
                    bookResvRepo.updateBookQuantity(book.getId());
                }
            } else {
                isBookAvailable = false;
                break;
            }
        }
        if (!isBookAvailable) {
            // create the reservation record (requires transaction)
            Resv resv = new Resv();
            resv.setFullName(reservePersonName);
            resv.setResvDate(Date.valueOf(LocalDate.now()));
            Integer reservationId = bookResvRepo.createResv(resv);

            // create the reservation details records (requires transaction)
            for (Book book : books) {
                ResvDetails resvDetails = new ResvDetails();
                resvDetails.setBookId(book.getId());
                resvDetails.setResvId(reservationId);

                bookResvRepo.createResvDetail(resvDetails);
            }
            isReservationCompleted = true;

        } 

        return isReservationCompleted;
    }
}

package nusiss.paf.day24transaction.payload;

import java.util.List;

import nusiss.paf.day24transaction.Model.Book;

public class BookResvRequest {
    
    private List<Book> bookList;
    private String fullName;

    public BookResvRequest() {
    }

    public BookResvRequest(List<Book> bookList, String fullName) {
        this.bookList = bookList;
        this.fullName = fullName;
    }
    
    public List<Book> getBookList() {
        return bookList;
    }
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
}

package nusiss.paf.day24transaction.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import nusiss.paf.day24transaction.Model.Book;
import nusiss.paf.day24transaction.Model.Resv;
import nusiss.paf.day24transaction.Model.ResvDetails;

@Repository
public class BookResvRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    // BOOK query strings
    private final String SELECT_BOOK_SQL = "select * from book";
    private final String CREATE_BOOK_SQL = "insert into book (title, quantity) values (?, ?)";
    private final String UPDATE_BOOK_BYID_SQL = "update book set quantity = quantity - 1 where id = ?";

    // RESV query strings
    private final String SELECT_RESV_SQL = "select * from resv";
    private final String CREATE_RESV_SQL = "insert into resv (resv_date, full_name) values (?, ?)";
    private final String UPDATE_RESV_BYID_SQL = "update resv set resv_date = ?, full_name = ? where id = ?";

    // RESV_DETAILS query string
    private final String CREATE_RESVDETAILS_SQL = "insert into resv_details (book_id, resv_id) values (?, ?)";

    // Method to retrieve all BOOK records
    public List<Book> findAllBook() {
        return jdbcTemplate.query(SELECT_BOOK_SQL, BeanPropertyRowMapper.newInstance(Book.class));
    }

    // Method to update the quantity of a BOOK record using the id
    public Boolean updateBookQuantity(int id) {
        int isUpdated = jdbcTemplate.update(UPDATE_BOOK_BYID_SQL, id);

        return isUpdated > 0; // returns true if updated
    }

    // Method to create a BOOK record
    public Integer createBook(Book book) {

        // create generated key holder object -> to return the primary key of the record
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder(); 
        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(CREATE_BOOK_SQL, new String[] {"id"});
                
                ps.setString(1, book.getTitle());
                ps.setInt(2, book.getQuantity());
                return ps;
            }
           
        };

        int rowsAffected = jdbcTemplate.update(psc, generatedKeyHolder); 

        // get auto-incremented id 
        Integer returnedId = generatedKeyHolder.getKey().intValue(); 

        return returnedId; 
    }

    // Method to retrieve all RESV records
    public List<Resv> findAllResv() {
        return jdbcTemplate.query(SELECT_RESV_SQL, BeanPropertyRowMapper.newInstance(Resv.class));
    }

    // Method to update RESV record by id
    public Boolean updateResv(int id) {
        int isUpdated = jdbcTemplate.update(UPDATE_RESV_BYID_SQL, id);

        return isUpdated > 0; // returns true if updated
    }

    // Method to create RESV record
    public Integer createResv(Resv resv) {

        // create generated key holder object -> to return the primary key of the record
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder(); 
        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(CREATE_RESV_SQL, new String[] {"id"});
                
                ps.setDate(1, resv.getResvDate());
                ps.setString(2, resv.getFullName());
                return ps;
            }
           
        };

        int rowsAffected = jdbcTemplate.update(psc, generatedKeyHolder); 

        // get auto-incremented id 
        Integer returnedId = generatedKeyHolder.getKey().intValue(); 

        return returnedId; 
    }

    // Method to create RESV_DETAILS record
    public Integer createResvDetail(ResvDetails resvD) {

        // create generated key holder object -> to return the primary key of the record
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder(); 
        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(CREATE_RESVDETAILS_SQL, new String[] {"id"});
                
                ps.setInt(1, resvD.getBookId());
                ps.setInt(2, resvD.getResvId());
                return ps;
            }
           
        };

        int rowsAffected = jdbcTemplate.update(psc, generatedKeyHolder); 

        // get auto-incremented id 
        Integer returnedId = generatedKeyHolder.getKey().intValue(); 

        return returnedId; 
    }
}

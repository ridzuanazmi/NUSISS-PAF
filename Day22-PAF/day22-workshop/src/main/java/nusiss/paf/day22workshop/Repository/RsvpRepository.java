package nusiss.paf.day22workshop.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nusiss.paf.day22workshop.Model.Rsvp;

@Repository
public class RsvpRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSQL = "select * from rsvp";

    // private final String countSQL = "select count(*) from rsvp";

    private final String findByNameSQL = "select * from rsvp where full_name like ?";
    private final String findByIdSQL = "select * from rsvp where id = ?";
    private final String findByEmailSQL = "select * from rsvp where email = ?";

    private final String insertNewSQL = "insert into rsvp (full_name, email, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";

    private final String updateByIdSQL = "update rsvp set full_name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ? where id = ?";

    // Method to get all records in Rsvp schema
    public List<Rsvp> getAll() {
        return jdbcTemplate.query(findAllSQL, new BeanPropertyRowMapper<>(Rsvp.class));
        // BeanPropertyRowMapper is required so that it can map the column name and it's value in the row 
    }

    // method to get the record by Id
    public Rsvp getRsvpbyId(Integer id) {
        return jdbcTemplate.queryForObject(findByIdSQL, new BeanPropertyRowMapper<>(Rsvp.class), id);
    }

    // Method to get the record by part of a name using '%name%'
    public List<Rsvp> getRsvpByName(String fullName) {
        return jdbcTemplate.query(findByNameSQL, BeanPropertyRowMapper.newInstance(Rsvp.class), "%" + fullName + "%");
        // return jdbcTemplate.queryForObject(findByNameSQL, BeanPropertyRowMapper.newInstance(Rsvp.class), "%" + fullName + "%");
    }

    // Method to get the record by email
    public Rsvp getRsvpByEmail(String email) {
        return jdbcTemplate.queryForObject(findByEmailSQL, BeanPropertyRowMapper.newInstance(Rsvp.class), email);
    }

    // Method to create new Rsvp
    public Boolean create(Rsvp rsvp) {
        Integer result = jdbcTemplate.update(insertNewSQL, 
                rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments());

        return result > 0 ? true : false;
    }

    // Method to update existing Rsvp
    public Boolean update(Rsvp rsvp) {
        Integer result = jdbcTemplate.update(updateByIdSQL, 
                rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getId());

        return result > 0 ? true : false;
    }

    // Method to inserts a list of RSVP records into the database using JDBC's batch update feature
    // Different from slides, Darryl's method
    public int[] batchUpdate(List<Rsvp> rsvpList) {

        return jdbcTemplate.batchUpdate(insertNewSQL, new BatchPreparedStatementSetter() {
            
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, rsvpList.get(i).getFullName());
                ps.setString(2, rsvpList.get(i).getEmail());
                ps.setString(3, rsvpList.get(i).getPhone());
                ps.setDate(4, rsvpList.get(i).getConfirmationDate());
                ps.setString(5, rsvpList.get(i).getComments());
            }

            public int getBatchSize() {
                return rsvpList.size();
            }
        });
    }

    // Update a 
}

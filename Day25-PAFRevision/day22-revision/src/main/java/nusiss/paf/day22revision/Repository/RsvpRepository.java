package nusiss.paf.day22revision.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import nusiss.paf.day22revision.Model.Rsvp;

@Repository
public class RsvpRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_RSVP_SQL = "select * from rsvp";
    private static final String SELECT_RSVP_BYNAME_SQL = "select * from rsvp where full_name like ?";
    private static final String SELECT_RSVP_BYID_SQL = "select * from rsvp where id = ?";
    private static final String CREATE_RSVP_SQL = "insert into rsvp (full_name, email, phone, confirmation_date, comments) values (?, ?, ?, ? ,?)";
    private static final String UPDATE_RSVP_BYID_SQL = "update rsvp set full_name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ? where id = ?";
    private static final String DELETE_RSVP_BYID_SQL = "delete from rsvp where id = ?";

    // Method to get all records from rsvp table
    public List<Rsvp> getAllRsvp() {
        return jdbcTemplate.query(SELECT_ALL_RSVP_SQL, BeanPropertyRowMapper.newInstance(Rsvp.class));
    }

    // Method to get all records from rsvp table by part of full_name
    public List<Rsvp> getRsvpByName(String fullName) {
        return jdbcTemplate.query(SELECT_RSVP_BYNAME_SQL, BeanPropertyRowMapper.newInstance(Rsvp.class), "%" + fullName + "%");
    }

    // Method to get a record from rsvp by id
    public Rsvp getRsvpById(Integer id) {
        return jdbcTemplate.queryForObject(SELECT_RSVP_BYID_SQL, BeanPropertyRowMapper.newInstance(Rsvp.class), id);
    }

    // Method1 to create new rsvp record simple method
    public Boolean createRsvp1(Rsvp rsvp) {

        Integer result = jdbcTemplate.update(CREATE_RSVP_SQL, rsvp.getFullName(), rsvp.getEmail(), 
                rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments());

        return result > 0;
    }

    // Method2 to create new rsvp using PSS
    public Boolean createRsvp2(Rsvp rsvp) {

        PreparedStatementCallback<Boolean> psc = new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, rsvp.getFullName());
                ps.setString(2, rsvp.getEmail());
                ps.setString(3, rsvp.getPhone());
                ps.setDate(4, rsvp.getConfirmationDate());
                ps.setString(5, rsvp.getComments());
                return ps.execute();
            } // end of doInPreparedStatement()
        }; // end of PreparedStatementCallback()

        Boolean isCreated = jdbcTemplate.execute(CREATE_RSVP_SQL, psc);
        return isCreated;
    }

    // Method1 to update rsvp using update()
    public Boolean updateRsvp1(Rsvp rsvp) {

        Integer isUpdated = jdbcTemplate.update(UPDATE_RSVP_BYID_SQL, 
                rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getId());

        return isUpdated > 0;
    }

    // Method to delete an rsvp record using id 
    public Boolean deleteRsvpById(Integer id) {
        Integer isDeleted = jdbcTemplate.update(DELETE_RSVP_BYID_SQL, id);

        return isDeleted > 0;
    }
}

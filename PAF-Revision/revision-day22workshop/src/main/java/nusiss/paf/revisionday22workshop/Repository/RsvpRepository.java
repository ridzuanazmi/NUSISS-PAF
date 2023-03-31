package nusiss.paf.revisionday22workshop.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nusiss.paf.revisionday22workshop.Model.Rsvp;

@Repository
public class RsvpRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_RSVP_SQL = "select * from rsvp";
    private static final String SELECT_RSVP_BYNAME_SQL = "select * from rsvp where full_name like ?";
    private static final String CREATE_RSVP = "insert into rsvp (full_name, email, phone, confirmation_date, comments) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_RSVP_BYEMAIL_SQL = "select * from rsvp where email like ?";
    private static final String UPDATE_RSVP_BYID_SQL = "update rsvp set full_name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ? where id = ?";
    private static final String DELETE_RSVP_BYID_SQL = "delete from rsvp where id = ?";

    public List<Rsvp> getAllRsvp() {

        return jdbcTemplate.query(
                SELECT_ALL_RSVP_SQL,
                BeanPropertyRowMapper.newInstance(Rsvp.class));
    }

    public Optional<Rsvp> getRsvpByName(String name) {

        Rsvp rsvp = jdbcTemplate.queryForObject(
                SELECT_RSVP_BYNAME_SQL,
                BeanPropertyRowMapper.newInstance(Rsvp.class),
                "%" + name + "%");

        return Optional.ofNullable(rsvp);
    }

    public Optional<Rsvp> getRsvpByEmail(String email) {

        Rsvp rsvp = jdbcTemplate.queryForObject(
                SELECT_RSVP_BYEMAIL_SQL,
                BeanPropertyRowMapper.newInstance(Rsvp.class),
                email);

        return Optional.ofNullable(rsvp);
    }

    public Boolean createRsvp(Rsvp rsvp) {

        int isCreated = jdbcTemplate.update(
                CREATE_RSVP, rsvp.getFullName(), rsvp.getEmail(),
                rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments());
        return isCreated > 0;
    }

    public boolean updateRsvp(Rsvp rsvp) {

        int isUpdated = jdbcTemplate.update(
                UPDATE_RSVP_BYID_SQL, rsvp.getFullName(), rsvp.getEmail(),
                rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getId());
        return isUpdated > 0;
    }

    public Boolean deleteRsvpById(Integer id) {

        int isDeleted = jdbcTemplate.update(DELETE_RSVP_BYID_SQL, id);
        return isDeleted > 0;
    }
}

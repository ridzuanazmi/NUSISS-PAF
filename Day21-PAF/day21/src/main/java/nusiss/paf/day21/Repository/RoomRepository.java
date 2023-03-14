package nusiss.paf.day21.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import nusiss.paf.day21.Model.Room;

@Repository
public class RoomRepository implements RoomRepositoryImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSQL = "select * from room";

    private final String countSQL = "select count(*) from room";

    private final String findAllByIdSQL = "select * from room where id = ?";

    private final String deleteByIdSQL = "delete from room where id = ?";

    private final String insertSQL = "insert into room (room_type, price) values (?, ?)";

    private final String updateSQL = "update room set price = ? where id = ?";

    @Override
    public int count() {
        Integer result = 0;
        result = jdbcTemplate.queryForObject(countSQL, Integer.class);

        if (null == result) {
            return 0;
        } else {
            return result;
        }
    }

    // CREATE new room
    @Override
    public Boolean save(Room room) {

        Boolean saved = false;
        saved = jdbcTemplate.execute(insertSQL, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setString(1, room.getRoomType());
                ps.setFloat(2, room.getPrice());
                Boolean rslt = ps.execute();
                return rslt;
            }
        });
        return saved;
    }

    // Get all room types in the schema
    @Override
    public List<Room> getAll() {

        List<Room> roomList = new ArrayList<>();

        roomList = jdbcTemplate.query(findAllSQL, BeanPropertyRowMapper.newInstance(Room.class));

        return roomList;
    }

    // GET Room from the id
    @Override
    public Room getRoomById(Integer id) {

        return jdbcTemplate.queryForObject(findAllByIdSQL, BeanPropertyRowMapper.newInstance(Room.class), id);
    }

    // UPDATE room
    @Override
    public int update(Room room) {

        int updated = 0;
        updated = jdbcTemplate.update(updateSQL, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setFloat(1, room.getPrice());
                ps.setInt(2, room.getId());
            }
        });
        return updated;
    }

    public int deleteById(Integer id) {
        
        int deleted = 0;

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException{
                ps.setInt(1, id);
            }
            
        };
        deleted = jdbcTemplate.update(deleteByIdSQL, pss);

        return deleted;
    }

}

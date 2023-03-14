package nusiss.paf.day23.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import nusiss.paf.day23.Model.Dependent;

@Repository
public class DependentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllDepSQL = "select * from dependent";

    private final String createDepSQL = "insert into dependent (employee_id, full_name, relationship, birthdate) values (?, ?, ?, ?)";

    private final String deleteDepByIdSQL = "delete from dependent where id = ?";

    private final String updateDepByIdSQL = "update dependent set employee_id= ?, full_name = ?, relationship = ?, birthdate = ? where id = ?";

    // Method to find all dependent record
    public List<Dependent> findAllDep() {
        return jdbcTemplate.query(findAllDepSQL, BeanPropertyRowMapper.newInstance(Dependent.class));
    }

    // Method to create a dependent record
    public Boolean createDep(Dependent dep) {
        Integer result = jdbcTemplate.update(createDepSQL, dep.getEmployeeId(),
                dep.getFullName(), dep.getRelationship(), dep.getBirthDate());

        return result > 0;
    }

    // Method to delete a dependent record
    public Boolean deleteDepById(Integer id) {
        Integer deleteDep = jdbcTemplate.update(deleteDepByIdSQL, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        });
        return deleteDep > 0;
    }

    // Method to update an existing dependent record
    public Boolean updateDepById(Dependent dep) {
        
        Integer isUpdated = jdbcTemplate.update(updateDepByIdSQL, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
               // employee_id= ?, full_name = ?, relationship = ?, birthdate = ? where id = ?
               ps.setInt(1, dep.getEmployeeId());
               ps.setString(2, dep.getFullName());
               ps.setString(3, dep.getRelationship());
               ps.setDate(4, dep.getBirthDate());
               ps.setInt(5, dep.getId());
            }
        });
        return isUpdated > 0;
    }
}

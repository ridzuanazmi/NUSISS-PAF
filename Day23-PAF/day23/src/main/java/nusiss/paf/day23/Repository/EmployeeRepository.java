package nusiss.paf.day23.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import nusiss.paf.day23.Model.Dependent;
import nusiss.paf.day23.Model.Employee;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllEmployeesSQL = "select * from employee";

    private final String findAllInnerJoinSQL = "select emp.id, emp.first_name, emp.last_name, emp.salary, " +
            "dep.id dep_id, dep.full_name dep_full_name, dep.relationship, dep.birthdate " +
            "from employee as emp inner join dependent as dep on emp.id = dep.employee_id";

    private final String findInnjerJoinByIdSQL = "select emp.id, emp.first_name, emp.last_name, emp.salary," +
            "dep.id dep_id, dep.full_name dep_full_name, dep.relationship, dep.birthdate " +
            "from employee as emp left join dependent as dep on emp.id = dep.employee_id where emp.id = ?";
    private final String findEmpByIdSQL = "select * from employee where id = ?";

    private final String insertSQL = "insert into employee (first_name, last_name, salary) values (?, ?, ?)";

    private final String updateSQL = "update employee set first_name = ?, last_name = ?, salary = ? where id = ?";
    private final String deleteDepByEmpIdSQL = "delete from dependent where employee_id = ?";

    private final String deleteByIdSQL = "delete from employee where id = ?";

    public List<Employee> findAllEmployees() {
        return jdbcTemplate.query(findAllEmployeesSQL, BeanPropertyRowMapper.newInstance(Employee.class));
    }

    // Method to create an employee record in MySQL
    public Boolean createEmp(Employee employee) {

        Boolean isSaved = false;

        PreparedStatementCallback<Boolean> psc = new PreparedStatementCallback<Boolean>() {

            @Override
            @Nullable
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setInt(3, employee.getSalary());
                return ps.execute();
            }
        };
        isSaved = jdbcTemplate.execute(insertSQL, psc);
        return isSaved;
    }

    // In the industry use PreparedStatement to prevent hacking.
    public int updateEmp(Employee employee) {

        int isUpdated = 0;

        isUpdated = jdbcTemplate.update(updateSQL, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setInt(3, employee.getSalary());
                ps.setInt(4, employee.getId());
            }
        });

        return isUpdated;
    }

    // Method to delete Employee record together with its dependent
    public int deleteEmpById(Integer id) {
        // This wont work as employee table has dependent records associated with it in
        // the "dependent" table. The foreign key constraint "dependent_employee_fk"
        // prevents you from deleting the parent record in the "employee" table while
        // there are still dependent records referencing it in the "dependent" table
        int isDeleted = 0;

        // Must allow the id to be set as null instead of not null.
        jdbcTemplate.update(deleteDepByEmpIdSQL, id);

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        };

        isDeleted = jdbcTemplate.update(deleteByIdSQL, pss);

        return isDeleted;
    }

    // Methods below show different way to get inner join tables with one to many cardinality
    public List<Employee> findAllWithDep1() {
        List<Employee> employees = jdbcTemplate.query(findAllInnerJoinSQL, new ResultSetExtractor<List<Employee>>() {
            @Override
            public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Integer, Employee> employeeMap = new HashMap<>();
    
                while (rs.next()) {
                    int employeeId = rs.getInt("id");
                    Employee employee = employeeMap.get(employeeId);
    
                    if (employee == null) {
                        employee = new Employee();
                        employee.setId(employeeId);
                        employee.setFirstName(rs.getString("first_name"));
                        employee.setLastName(rs.getString("last_name"));
                        employee.setSalary(rs.getInt("salary"));
    
                        List<Dependent> dependents = new ArrayList<>();
                        employee.setDependents(dependents);
    
                        employeeMap.put(employeeId, employee);
                    }
    
                    Dependent dependent = new Dependent();
                    dependent.setId(rs.getInt("dep_id"));
                    dependent.setEmployeeId(rs.getInt("id"));
                    dependent.setFullName(rs.getString("dep_full_name"));
                    dependent.setRelationship(rs.getString("relationship"));
                    dependent.setBirthDate(rs.getDate("birthdate"));
    
                    employee.getDependents().add(dependent);
                }
    
                return new ArrayList<>(employeeMap.values());
            }
        });
    
        return employees;
    }

    // this method to find all employees with dependents works as well
    public List<Employee> findAllWithDep2() {

        List<Employee> empList = new ArrayList<>();

        // The jdbcTemplate.query() method is used to execute the SQL query and retrieve
        // the results. The ResultSetExtractor is used to extract data from the
        // ResultSet and map it to a List of Employee objects
        empList = jdbcTemplate.query(findAllInnerJoinSQL, new ResultSetExtractor<List<Employee>>() {

            @Override
            // The extractData() method of the ResultSetExtractor is implemented to extract
            // the data from the ResultSet and map it to a List of Employee objects
            public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {

                List<Employee> employeeList = new ArrayList<>();

                // The while loop is used to iterate over the rows of the ResultSet. For each
                // row, a new Employee object is created and its properties are set based on the
                // values in the ResultSet.
                while (rs.next()) {
                    // emp,id as emp_id, emp.first_name, emp.last_name, emp.salary
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setSalary(rs.getInt("salary"));
                    employee.setDependents(new ArrayList<Dependent>());

                    // dep.id dep_id, dep.full_name dep_full_name, dep.relationship
                    // Similarly, a new Dependent object is created for each row, and its properties
                    // are set based on the values in the ResultSet
                    Dependent dependent = new Dependent();
                    dependent.setId(rs.getInt("dep_id"));
                    dependent.setEmployeeId(rs.getInt("id"));
                    dependent.setFullName(rs.getString("dep_full_name"));
                    dependent.setRelationship(rs.getString("relationship"));
                    dependent.setBirthDate(rs.getDate("birthdate"));

                    // Next, the code checks if the employeeList is empty. If it is, the dependent
                    // object is added to the Employee object's list of dependents, and the Employee
                    // object is added to the employeeList
                    if (0 == employeeList.size()) {
                        employee.getDependents().add(dependent);
                        employeeList.add(employee);
                        // If the employeeList is not empty, the code checks if the Employee object
                        // already exists in the list. If it does, the dependent object is added to the
                        // existing Employee object's list of dependents. If it doesn't, a new Employee
                        // object is created with the dependent object added to its list of dependents,
                        // and the Employee object is added to the employeeList
                    } else {
                        List<Employee> temporaryList = employeeList.stream().filter(e -> e.getId() == employee.getId())
                                .collect(Collectors.toList());

                        if (temporaryList.size() > 0) {
                            int idx = employeeList.indexOf(temporaryList.get(0));

                            if (idx >= 0) {
                                employeeList.get(idx).getDependents().add(dependent);
                            }
                        } else {
                            // if employee record is not founf in the list of employees, add a new employee
                            // record together with the dependent info
                            employee.getDependents().add(dependent);
                            employeeList.add(employee);
                        }
                    }
                }
                return employeeList;
            }
        });
        return empList;
    }
    // So, in summary, the readAll() method uses the jdbcTemplate to execute the SQL
    // query and process the ResultSet to create a list of Employee objects. The
    // ResultSetExtractor interface is used to extract data from the ResultSet and
    // map it to Employee objects. The code also handles the mapping of Dependent
    // objects to their corresponding Employee objects, based on the employee_id
    // foreign key relationship in the database

    public Employee findInnerJoinById(Integer id) {

        Employee employee = new Employee();

        employee = jdbcTemplate.query(findInnjerJoinByIdSQL, new ResultSetExtractor<Employee>() {

            @Override
            public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {

                Employee emp = new Employee();

                while (rs.next()) {
                    // emp,id as emp_id, emp.first_name, emp.last_name, emp.salary
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setSalary(rs.getInt("salary"));
                    employee.setDependents(new ArrayList<Dependent>());

                    // dep.id dep_id, dep.full_name dep_full_name, dep.relationship
                    Dependent dependent = new Dependent();
                    dependent.setId(rs.getInt("dep_id"));
                    dependent.setEmployeeId(rs.getInt("id"));
                    dependent.setFullName(rs.getString("dep_full_name"));
                    dependent.setRelationship(rs.getString("relationship"));

                    if (rs.isFirst()) {
                        // First time need to add employee data with the dependents
                        emp = employee;
                        emp.getDependents().add(dependent);
                    } else {
                        // employee data already in database, just add extra dependent
                        emp.getDependents().add(dependent);
                    }
                }
                return emp;
            }
        }, id); // the third argument

        return employee;
    }
}

package nusiss.paf.day21.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nusiss.paf.day21.Model.Customer;

@Repository
public class CustomerRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate; // the jdbcTemplate is used to read/write from repo to MySQL

    private final String findAllSQL = "select * from customer"; // This is the query string

    private final String findAllSQLLimitOffset = "select * from customer limit ? offset ?";

    private final String findByIdSQL = "select * from customer where id = ?";

    // Method
    public List<Customer> getAllCustomer() {

        final List<Customer> customerList = new ArrayList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQL);

        // This creates the Customer object from the MySQL database
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setDob(rs.getDate("dob"));
            customerList.add(customer);
        }

        return Collections.unmodifiableList(customerList);
    }

    // Method Limit Offset
    public List<Customer> getAllCustomerLimitOffset(int limit, int offset) {

        final List<Customer> customerList = new ArrayList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQLLimitOffset, limit, offset);

        // This creates the Customer object from the MySQL database
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setDob(rs.getDate("dob"));
            customerList.add(customer);
        }

        return Collections.unmodifiableList(customerList);
    }

    // Method findById. This is a different way compared to above
    public Customer getCustomerById(int id) {

        return jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Customer.class), id);
    }
}

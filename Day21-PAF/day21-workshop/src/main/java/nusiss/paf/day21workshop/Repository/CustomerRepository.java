package nusiss.paf.day21workshop.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nusiss.paf.day21workshop.Model.Customer;
import nusiss.paf.day21workshop.Model.Order;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSQL = "select * from customers";

    private final String findAllLimitOffsetSQL = "select * from customers limit ? offset ? ";

    private final String findByIdSQL = "select * from customers where id = ?";

    private final String findOrderByCustomerIdSQL = "select * from orders where customer_id = ?";

    // This method gets all Customers from MySQL
    public List<Customer> getAllCustomer() {

        final List<Customer> customerList = new ArrayList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQL);

        // This creates the Customer object from the MySQL database
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setCompany(rs.getString("company"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setEmail(rs.getString("email_address"));
            customer.setJobTitle(rs.getString("job_title"));
            customer.setBusinessPhone(rs.getString("business_phone"));
            customer.setHomePhone(rs.getString("home_phone"));
            customer.setMobilePhone(rs.getString("mobile_phone"));
            customer.setFaxNumber(rs.getString("fax_number"));
            customer.setAddress(rs.getString("address"));
            customer.setCity(rs.getString("city"));
            customer.setStateProvince(rs.getString("state_province"));
            customer.setZipPostalCode(rs.getString("zip_postal_code"));
            customer.setCountry(rs.getString("country_region"));
            customer.setWebPage(rs.getString("web_page"));
            customer.setNotes(rs.getString("notes"));
            customerList.add(customer);
        }

        return Collections.unmodifiableList(customerList);
    }

    // Method Limit Offset
    public List<Customer> getAllCustomerLimitOffset(int limit, int offset) {

        final List<Customer> customerList = new ArrayList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllLimitOffsetSQL, limit, offset);

        // This creates the Customer object from the MySQL database
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setCompany(rs.getString("company"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setEmail(rs.getString("email_address"));
            customer.setJobTitle(rs.getString("job_title"));
            customer.setBusinessPhone(rs.getString("business_phone"));
            customer.setHomePhone(rs.getString("home_phone"));
            customer.setMobilePhone(rs.getString("mobile_phone"));
            customer.setFaxNumber(rs.getString("fax_number"));
            customer.setAddress(rs.getString("address"));
            customer.setCity(rs.getString("city"));
            customer.setStateProvince(rs.getString("state_province"));
            customer.setZipPostalCode(rs.getString("zip_postal_code"));
            customer.setCountry(rs.getString("country_region"));
            customer.setWebPage(rs.getString("web_page"));
            customer.setNotes(rs.getString("notes"));
            customerList.add(customer);
        }

        return Collections.unmodifiableList(customerList);
    }

    // Method getCustomerbyId. Get 1 customer info
    public Optional<Customer> getCustomerById(int id) {

        try {
            Customer customer = jdbcTemplate.queryForObject(findByIdSQL,
                    BeanPropertyRowMapper.newInstance(Customer.class), id);
            return Optional.of(customer);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // Method getOrderByCustomerId. Get multiple Order info
    public Optional<List<Order>> getOrderByCustomerId(int customerId) {

        try {
            // In this updated version of your code, we are using a
            // RowMapperResultSetExtractor to map the query result to a list of Order
            // objects. The RowMapperResultSetExtractor takes a RowMapper as a parameter, so
            // we are using the BeanPropertyRowMapper to automatically map the columns of
            // the query result to the fields of the Order class. 
            // Note that we are also using a lambda expression to set the parameter values of the prepared
            // statement. This is equivalent to using the Object[] args parameter in the
            // deprecated query method.
            final List<Order> orderList = jdbcTemplate.query(findOrderByCustomerIdSQL, ps -> ps.setInt(1, customerId),
                    new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(Order.class)));
            return Optional.of(orderList);

            // The method query(String, Object[], RowMapper<Order>) from the type
            // JdbcTemplate is deprecated
            // final List<Order> orderList = jdbcTemplate.query(findOrderByCustomerIdSQL,
            // new Object[]{customerId}, new BeanPropertyRowMapper<>(Order.class));
            // return Optional.of(orderList);

            /*
             * This does not work as the queryForList method of the JdbcTemplate class,
             * which returns a list of Map objects, not a list of Order objects. The Map
             * objects contain the column names as keys and the corresponding values from
             * the query result as values
             * final List<Order> orderList =
             * jdbcTemplate.queryForList(findOrderByCustomerIdSQL, Order.class, customerId);
             * return Optional.of(orderList);
             */
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

package nusiss.paf.revisionday21workshop.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nusiss.paf.revisionday21workshop.Model.Customer;
import nusiss.paf.revisionday21workshop.Model.Order;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_CUSTOMERS_LIMIT_SQL = "select * from customers limit ? offset ?";
    private static final String SELECT_CUSTOMER_BYID_SQL = "select * from customers where id = ?";
    private static final String SELECT_ORDERS_BYID_SQL = "select customer_id, order_date, shipped_date, ship_name, ship_address, ship_city, ship_country_region, notes from orders where customer_id = ?"; 

    // Returns a list of customer with limit and offset argument
    public List<Customer> getAllCustomers(Integer limit, Integer offset) {

        return jdbcTemplate.query(
                SELECT_CUSTOMERS_LIMIT_SQL,
                BeanPropertyRowMapper.newInstance(Customer.class),
                limit, offset);
    }

    // Returns a Customer object from customers table via id
    public Optional<Customer> getCustomerById(Integer id) {

        Customer customer = jdbcTemplate.queryForObject(
                SELECT_CUSTOMER_BYID_SQL,
                BeanPropertyRowMapper.newInstance(Customer.class),
                id);
        return Optional.of(customer);
    }

    // Returns an Order object from orders table via customer_id
    // THIS METHOD DOES NOT WORK DUE TO AN ISSUE WITH THE DATE IMPORT
    public List<Order> getOrderById(Integer id) {

        final List<Order> orderList = new ArrayList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ORDERS_BYID_SQL, id);

        // Create the Order object with selected 
        // fields from the orders table in MySQL
        while (rs.next()) {
            Order order = new Order();
            order.setCustomerId(rs.getInt("customer_id"));
            // order.setOrderDate(rs.getTimestamp("order_date"));
            // order.setShippedDate(rs.getTimestamp("shipped_date"));
            order.setShipName(rs.getString("ship_name"));
            order.setShipAddress(rs.getString("ship_address"));
            order.setShipCity(rs.getString("ship_city"));
            order.setShipCountry(rs.getString("ship_country_region"));
            order.setNotes(rs.getString("notes"));
            orderList.add(order);
        }

        return (Collections.unmodifiableList(orderList));
    }

    // Returns an Order object from orders table via customer_id
    // This method works, but have to query string rows manually in, instead of using the SqlRowSet
    public List<Order> getOrderById2(Integer id) {
        return jdbcTemplate.query(SELECT_ORDERS_BYID_SQL, BeanPropertyRowMapper.newInstance(Order.class), id);
    }
}

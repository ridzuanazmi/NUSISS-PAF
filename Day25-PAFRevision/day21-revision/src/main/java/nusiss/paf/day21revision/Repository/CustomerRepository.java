package nusiss.paf.day21revision.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nusiss.paf.day21revision.Model.Customer;
import nusiss.paf.day21revision.Model.Order;

@Repository
public class CustomerRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SELECT_ALL_CUSTOMERS_SQL = "select * from customers";

    private final String SELECT_CUSTOMERS_LIMIT_SQL = "select * from customers limit ? offset ?";

    private final String SELECT_CUSTOMER_BYID_SQL = "select * from customers where id = ?";

    private final String SELECT_ORDER_CUSTOMER_BYID_SQL = "select * from orders where customer_id = ?";

    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS_SQL, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    public List<Customer> getLimitCustomers(int limit, int offset) {
        return jdbcTemplate.query(SELECT_CUSTOMERS_LIMIT_SQL, BeanPropertyRowMapper.newInstance(Customer.class), limit, offset);
    }

    public List<Order> getOrderByCustId(Integer id) {
        return jdbcTemplate.query(SELECT_ORDER_CUSTOMER_BYID_SQL, BeanPropertyRowMapper.newInstance(Order.class), id);
    }
}

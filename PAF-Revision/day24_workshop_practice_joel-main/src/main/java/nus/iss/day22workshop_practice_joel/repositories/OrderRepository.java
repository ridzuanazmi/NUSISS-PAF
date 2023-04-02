package nus.iss.day22workshop_practice_joel.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import nus.iss.day22workshop_practice_joel.models.Order;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_ORDER_SQL = 
    "INSERT INTO orders (order_date, customer_name, ship_address, notes, tax) " +
    "VALUES (?, ?, ?, ?, ?)";

    private static final String INSERT_ORDER_DETAIL_SQL = 
    "INSERT INTO order_details (order_id, product, unit_price, discount, quantity) VALUES (?, ?, ?, ?, ?)";

    private static final String NEXTID_SQL = "SELECT MAX(order_id) FROM orders";

    public int getNextOrderId() {
        Integer nextId = jdbcTemplate.queryForObject(NEXTID_SQL, Integer.class);
        return nextId == null ? 1 : nextId + 1;
    }

    public int insertOrder(Order order) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder(); //this is when u need to extract the auto incremented key from SQL

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_ORDER_SQL, new String[] {"order_id"});
                ps.setDate(1, order.getOrderDate());
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getShipAddress());
                ps.setString(4, order.getNotes());
                ps.setFloat(5, order.getTax());
                return ps;
            }
        };

        jdbcTemplate.update(psc, generatedKeyHolder);

        Integer returnedId = generatedKeyHolder.getKey().intValue();


        return returnedId;
    }

    public Boolean insertOrderDetail(int orderId, String product, float unitPrice, float discount, int quantity) {
        int success = jdbcTemplate.update(INSERT_ORDER_DETAIL_SQL, orderId, product, unitPrice, discount, quantity);

        return success > 0? true : false;
    }
    
}

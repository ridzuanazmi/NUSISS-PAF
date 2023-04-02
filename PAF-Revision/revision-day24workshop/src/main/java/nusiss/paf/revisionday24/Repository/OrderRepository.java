package nusiss.paf.revisionday24.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nusiss.paf.revisionday24.Model.Order;
import nusiss.paf.revisionday24.Model.OrderDetails;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_NEW_ORDERLIST_SQL = """
            insert into order_details (order_id, product, unit_price, discount, quantity)
            values (?, ?, ?, ?, ?)
            """;

    private static final String CREATE_NEW_ORDERS_SQL = """
            insert into orders (order_date, customer_name, ship_address, notes, tax)
            values (?, ?, ?, ?, ?)
            """;

    private static final String SELECT_ORDERS_BYID_SQL = "select * from orders where id = ?";
    private static final String SELECT_ORDERS_SQL = "select * from orders";
    private static final String SELECT_ORDERDETAILS_BYID_SQL = "select * from order_details where order_id = ?";
    private static final String SELECT_ORDERDETAILS_SQL = "select * from order_details";
    private static final String NEXT_ID_SQL = "select max(id) from orders";

    public Boolean createOrderDetails(OrderDetails od) {

        int isCreated = jdbcTemplate.update(
                CREATE_NEW_ORDERLIST_SQL, od.getOrderId(), od.getProduct(), od.getUnitPrice(),
                od.getDiscount(), od.getQuantity());

        return isCreated > 0;
    }

    public Boolean createOrder(Order o) {

        int isCreated = jdbcTemplate.update(
                CREATE_NEW_ORDERS_SQL, o.getOrderDate(), o.getCustomerName(),
                o.getShipAddress(), o.getNotes(), o.getTax());

        return isCreated > 0;
    }

    public Optional<Order> selectOrderById(Integer id) {

        Order order = jdbcTemplate.queryForObject(
                SELECT_ORDERS_BYID_SQL,
                BeanPropertyRowMapper.newInstance(Order.class),
                id);

        return Optional.ofNullable(order);
    }

    public List<Order> selectOrders() {

        return jdbcTemplate.query(
                SELECT_ORDERS_SQL,
                BeanPropertyRowMapper.newInstance(Order.class));
    }

    public List<OrderDetails> selectOrderDetailsById(Integer id) {

        return jdbcTemplate.query(
                SELECT_ORDERDETAILS_BYID_SQL,
                BeanPropertyRowMapper.newInstance(OrderDetails.class),
                id);
    }

    public List<OrderDetails> selectOrderDetails() {

        return jdbcTemplate.query(
                SELECT_ORDERDETAILS_SQL,
                BeanPropertyRowMapper.newInstance(OrderDetails.class));
    }

    public Integer getLatestOrderId() {

        Integer nextId = jdbcTemplate.queryForObject(NEXT_ID_SQL, Integer.class);
        return nextId == null ? 1 : nextId;
    }

    public int[] batchInsertOrderDetails(int orderId, List<OrderDetails> orderDetailsList) {
        String sql = CREATE_NEW_ORDERLIST_SQL;

        int[] updateCounts = jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        OrderDetails orderDetails = orderDetailsList.get(i);
                        ps.setInt(1, orderId);
                        ps.setString(2, orderDetails.getProduct());
                        ps.setFloat(3, orderDetails.getUnitPrice());
                        ps.setFloat(4, orderDetails.getDiscount());
                        ps.setInt(5, orderDetails.getQuantity());
                    }

                    @Override
                    public int getBatchSize() {
                        return orderDetailsList.size();
                    }
                });

        return updateCounts;
    }
}

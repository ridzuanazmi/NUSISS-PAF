package nusiss.paf.day24workshopapi.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import nusiss.paf.day24workshopapi.Model.Order;
import nusiss.paf.day24workshopapi.Model.OrderDetail;

@Repository
public class OrderDetailsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Orders table String query for CRUD
    private static final String SELECT_ALL_ORDERS_SQL = "select * from orders";
    private static final String SELECT_ORDERS_BYID_SQL = "select * from orders where id = ?";
    private static final String CREATE_ORDERS_SQL = "insert into orders (order_date, customer_name, ship_address, notes, tax) values (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDERS_BYID_SQL = "update orders set order_date = ?, customer_name = ?, ship_address = ?, notes = ?, tax = ? where id = ?";
    private static final String DELETE_ORDERS_BYID_SQL = "delete from orders where id = ?";

    // Order_details String query for CRUD
    private static final String SELECT_ALL_ORDERDETAILS_SQL = "select * from order_details";
    private static final String SELECT_ORDERDETAILS_BYID_SQL = "select * from order_details where id = ?";
    private static final String CREATE_ORDERDETAILS_SQL = "insert into order_details (order_id, product, unit_price, discount, quantity) values (?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDERDETAILS_BYID_SQL = "update order_details set " +
            "order_id = ?, product = ?. unit_price = ?, discount = ?, quantity = ? where id = ?";
    private static final String DELETE_ORDERDETAILS_BYID_SQL = "delete from order_details where id = ?";

    // OD String query (combination of orders and order_details)
    private static final String SELECT_ALL_OD_INNERJOIN_SQL = "select o.id, o.customer_name, o.ship_address, o.order_date, " +
            "od.id as od_id, od.order_id, od.product, od.unit_price, od.discount, od.quantity, o.notes, o.tax " +
            "from orders as o " +
            "left join order_details as od " +
            "on o.id = od.order_id " +
            "order by o.id";

    public List<Order> getAllOrderWithDetails() {

        // ResultSetExtractor to process the result set and populate Order and
        // OrderDetail objects. It maintains a map of orders with their corresponding
        // order details as lists of products tied to each order
        List<Order> orderList = jdbcTemplate.query(SELECT_ALL_OD_INNERJOIN_SQL, new ResultSetExtractor<List<Order>>() {

            @Override
            public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {

                Map<Integer, Order> orderMap = new HashMap<>();

                while (rs.next()) {
                    Integer orderId = rs.getInt("id");
                    Order order = orderMap.get(orderId);

                    if (null == order) {
                        order = new Order();
                        order.setId(orderId);
                        order.setCustomerName(rs.getString("customer_name"));
                        order.setShipAddress(rs.getString("ship_address"));
                        order.setOrderDate(rs.getDate("order_date"));
                        order.setNotes(rs.getString("notes"));
                        order.setTax(rs.getFloat("tax"));
                        order.setOrderDetails(new ArrayList<>());
                        orderMap.put(orderId, order);
                    } // end of if loop

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(rs.getInt("od_id"));
                    orderDetail.setOrderId(orderId);
                    orderDetail.setProduct(rs.getString("product"));
                    orderDetail.setUnitPrice(rs.getFloat("unit_price"));
                    orderDetail.setDiscount(rs.getFloat("discount"));
                    orderDetail.setQuantity(rs.getInt("quantity"));

                    order.getOrderDetails().add(orderDetail);

                } // end of while loop

                return new ArrayList<>(orderMap.values());

            } // end of extractData() method
            
        }); // end of orderList variable

        return orderList;

    } // end of getAllOrderWithDetails() method
}

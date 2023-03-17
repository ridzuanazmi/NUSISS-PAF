package nusiss.paf.day24workshopapi.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllOrderSQL = "select * from orders";
    private final String findAllOrderDetSQL = "select * from order_details";

    private final String findAllOrderDetByIdSQL = "select * from orders where id = ?";
}

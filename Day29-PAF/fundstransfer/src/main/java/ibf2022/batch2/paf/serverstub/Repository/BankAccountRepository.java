package ibf2022.batch2.paf.serverstub.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.serverstub.Model.BankAccount;

@Repository
public class BankAccountRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String CHECK_BALANCE_SQL = "select balance from bank_account where customer_name like ?";
    private final String GET_ACCOUNT_SQL = "select * from  bank_account where customer_name like ?";
    private final String WITHDRAW_SQL = "update bank_account set balance = balance - ? where customer_name like ?";
    private final String DEPOSIT_SQL = "update bank_account set balance = balance + ? where customer_name like ?";

    // Get the Bank account details by id
    public BankAccount findAcctByName(String name) {
        
        BankAccount bankAccount = jdbcTemplate.queryForObject(
                GET_ACCOUNT_SQL, BeanPropertyRowMapper.newInstance(BankAccount.class), name);

        return bankAccount;
    }

    // Check if there is balance available
    public Boolean canWithdraw(String name, Float withdrawAmount) {

        Boolean canWithdraw = false;

        Float balance = jdbcTemplate.queryForObject(CHECK_BALANCE_SQL, Float.class, name);

        if (withdrawAmount <= balance) {
            canWithdraw = true;
        }

        return canWithdraw;
    }

    public Boolean withdrawAmount(String senderName, Float withdrawnAmount) {

        Integer isUpdated = 0;

        isUpdated = jdbcTemplate.update(WITHDRAW_SQL, withdrawnAmount, senderName);

        return isUpdated > 0;
    }

    public Boolean depositAmount(String receiverName, Float depositAmount) {

        Integer isUpdated = 0;

        isUpdated = jdbcTemplate.update(DEPOSIT_SQL, depositAmount, receiverName);

        return isUpdated > 0;
    } 
}

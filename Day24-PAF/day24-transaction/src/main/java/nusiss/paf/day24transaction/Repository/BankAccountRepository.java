package nusiss.paf.day24transaction.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import nusiss.paf.day24transaction.Model.BankAccount;

@Repository
public class BankAccountRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String CHECK_BALANCE_SQL = "select balance from bank_account where id = ?";
    private final String GET_ACCOUNT_SQL = "select * from  bank_account where id = ?";
    private final String WITHDRAW_SQL = "update bank_account set balance = balance - ? where id = ?";
    private final String DEPOSIT_SQL = "update bank_account set balance = balance + ? where id = ?";
    private final String CREATE_ACCOUNT_SQL = "insert into bank_account (full_name, is_active, acct_type, balance) values (?, ?, ?, ?)";

    // Method to return only the balance column from a bank_account record of a specific id
    public Boolean checkBalance(Integer id, Float withdrawnAmount) {

        Boolean isWithdrawnBalanceAvailable = false;

        Float returnedBalance = jdbcTemplate.queryForObject(CHECK_BALANCE_SQL, Float.class, id);

        if (withdrawnAmount <= returnedBalance) {
            isWithdrawnBalanceAvailable = true;
        }

        return isWithdrawnBalanceAvailable;
    }

    public BankAccount findAcctById(Integer id) {
        
        BankAccount bankAccount = jdbcTemplate.queryForObject(GET_ACCOUNT_SQL, BeanPropertyRowMapper.newInstance(BankAccount.class), id);

        return bankAccount;
    }

    public Boolean withdrawAmount(Integer id, Float withdrawnAmount) {

        Boolean isWithdrawn = false;
        Integer isUpdated = 0;

        isUpdated = jdbcTemplate.update(WITHDRAW_SQL, withdrawnAmount, id);

        if(isUpdated > 0) {
            isWithdrawn = true;
        }

        return isWithdrawn;
    }

    public Boolean depositAmount(Integer id, Float depositAmount) {

        Boolean isDeposited = false;

        Integer isUpdated = 0;

        isUpdated = jdbcTemplate.update(DEPOSIT_SQL, depositAmount, id);

        if (isUpdated > 0) {
            isDeposited = true;
        }

        return isDeposited;
    }

    public Boolean createAccount(BankAccount bankAccount) {

        Boolean isCreated = false;
        isCreated = jdbcTemplate.execute(CREATE_ACCOUNT_SQL, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, bankAccount.getFullName());
                ps.setBoolean(2, bankAccount.getIsActive());
                ps.setString(3, bankAccount.getAcctType());
                ps.setFloat(4, bankAccount.getBalance());
                return ps.execute();
            }
        });
        return isCreated;
    }


}

package ibf2022.batch2.paf.serverstub.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.batch2.paf.serverstub.Model.BankAccount;
import ibf2022.batch2.paf.serverstub.Model.Transactions;
import ibf2022.batch2.paf.serverstub.Repository.BankAccountRepository;
import ibf2022.batch2.paf.serverstub.Repository.TransactionsRepository;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccRepo;

    @Autowired
    private TransactionsRepository transactionsRepo;

    @Transactional
    public UUID transferMoney(String senderName, String receiverName, Float transferAmount) {

        // Retrieve the bank accounts
        BankAccount senderAcct = null;
        BankAccount receiverAcct = null;

        try {
            // Retrieve the bank accounts
            senderAcct = bankAccRepo.findAcctByName(senderName);
            receiverAcct = bankAccRepo.findAcctByName(receiverName);
        
            // Checks if both accounts exist
            if (null == senderAcct || null == receiverAcct) {
                throw new IllegalArgumentException("One or both accounts not found");
            }
        } catch (EmptyResultDataAccessException ex) {
            // Handle the exception, e.g., log the error or return an error response
            System.err.println(ex.getMessage());
            // Perform any additional actions as needed
        }

        // Check if the sender account has enough money
        if (!bankAccRepo.canWithdraw(senderName, transferAmount)) {
            throw new IllegalArgumentException("Insufficient balance to withdraw/transfer!");
        }

        // Updates both accounts
        if (bankAccRepo.withdrawAmount(senderName, transferAmount) &&
                bankAccRepo.depositAmount(receiverName, transferAmount)) {

            // If both operations were successful, generate a UUID and return it
            UUID transactionId = UUID.randomUUID();

            // Create new transaction and save it to mongo with the .save function
            Transactions transactions = new Transactions();
            transactions.setTransactionId(transactionId);
            transactions.setFrom(senderName);
            transactions.setTo(receiverName);
            transactions.setTransferAmount(transferAmount);
            transactionsRepo.save(transactions);
            
            return transactionId;

        } else {

            throw new TransactionSystemException("Cannot perform transfer");
        }
    }
}

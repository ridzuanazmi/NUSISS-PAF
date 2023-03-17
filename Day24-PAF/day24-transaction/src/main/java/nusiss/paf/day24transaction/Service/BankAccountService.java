package nusiss.paf.day24transaction.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import nusiss.paf.day24transaction.Model.BankAccount;
import nusiss.paf.day24transaction.Repository.BankAccountRepository;

@Service
public class BankAccountService {
    
    @Autowired
    BankAccountRepository bankAcctRepo;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Boolean transferMoney(Integer senderId, Integer receiverId, Float amount) {

        Boolean isTransferred = false;
        Boolean isWithdrawn = false;
        Boolean isDeposited = false;
        // Boolean CanWithdraw = false;

        BankAccount receiverAcct = null; // creates a receiver account
        BankAccount senderAcct = null; // creates a sender account
        Boolean isReceiverAcctValid = false; // checks if reveriver account is valid (active)
        Boolean isSenderAcctValid = false; // checks id sender account is valid (active)
        Boolean canProceed = false;

        // checks if bank accounts of sender and receiver are valid (active)
        // -----------------------------------------------------------------
        receiverAcct = bankAcctRepo.findAcctById(receiverId);
        senderAcct = bankAcctRepo.findAcctById(senderId);
        isReceiverAcctValid = receiverAcct.getIsActive();
        isSenderAcctValid = senderAcct.getIsActive();

        if (isReceiverAcctValid && isSenderAcctValid) {
            canProceed = true;
        }
        // -----------------------------------------------------------------

        // check withdrawn account has more money than withdrawal amount
        // -----------------------------------------------------------------
        if (canProceed) {
            if (senderAcct.getBalance() < amount) {
                canProceed = false;
            }
        }
        // -----------------------------------------------------------------

        // Perform the withdrawal and depositing
        // -----------------------------------------------------------------
        if (canProceed) {
            // Perform withdrawal (requires transaction)
            isWithdrawn = bankAcctRepo.withdrawAmount(senderId, amount);

            // if(isDeposited == false)
            //     throw new IllegalArgumentException("Simulate error before withdrawal");

            // Perform deposit (requires transactional)
            isDeposited = bankAcctRepo.depositAmount(receiverId, amount);
        }
        // -----------------------------------------------------------------

        // Check if transaction successful
        // -----------------------------------------------------------------
        if (isWithdrawn && isDeposited) {
            isTransferred = true;
        }
        // -----------------------------------------------------------------
        return isTransferred;
    }
}

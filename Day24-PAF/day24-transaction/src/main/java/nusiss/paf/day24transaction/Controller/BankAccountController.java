package nusiss.paf.day24transaction.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day24transaction.Service.BankAccountService;
import nusiss.paf.day24transaction.payload.TransferRequest;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    
    @Autowired
    BankAccountService bankAcctSrvc;

    @PostMapping
    public ResponseEntity<Boolean> transferMoney(@RequestBody TransferRequest transferRequest) {

        Boolean isTransferred = false;

        isTransferred = bankAcctSrvc.transferMoney(transferRequest.getAccountFrom(), 
                        transferRequest.getAccountTo(), transferRequest.getAmount());

        if (isTransferred) {
            return new ResponseEntity<Boolean>(isTransferred, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(isTransferred, HttpStatus.BAD_REQUEST);
        }
    }
}

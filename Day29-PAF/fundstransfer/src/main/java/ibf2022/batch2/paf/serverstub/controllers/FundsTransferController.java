package ibf2022.batch2.paf.serverstub.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.paf.serverstub.Payload.TransferRequest;
import ibf2022.batch2.paf.serverstub.Service.BankAccountService;

@RestController
public class FundsTransferController {

	@Autowired
	private BankAccountService bankAccSrvc;

	// POST /api/transfer, Accept: application/json, Content-Type: application/json
	// {srcAcct: "sdfsdf", destAcct: "asdad", amount: 1}
	@PostMapping(path = "api/transfer")
	public ResponseEntity<String> postTransfer(@RequestBody TransferRequest request) {

		try {
			// Perform the transfer
			UUID transactionId = bankAccSrvc.transferMoney(request.getSrcAcct(), request.getDestAcct(),
					request.getAmount());

			// Transfer successful, return the JSON object
			String response = String.format("{\"transactionId\": \"%s\"}", transactionId.toString());
			System.out.printf(">>>> payload: %s\n", request);
			return ResponseEntity.ok(response);

		} catch (IllegalArgumentException | TransactionSystemException ex) {
			// Transfer failed, return the JSON object with the error message
			String response = String.format("{\"message\": \"%s\"}", ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		// Transfer successful return the following JSON object
		// { "transactionId", "aTransactionId" }
		//
		// Transfer failed return the following JSON object
		// { "message", "Error message" }

		// return null;
	}
}

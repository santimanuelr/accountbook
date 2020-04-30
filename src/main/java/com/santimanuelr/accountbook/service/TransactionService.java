package com.santimanuelr.accountbook.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.santimanuelr.accountbook.domain.Balance;
import com.santimanuelr.accountbook.domain.Transaction;
import com.santimanuelr.accountbook.repository.BalanceRepository;
import com.santimanuelr.accountbook.web.rest.Exceptions.NegativeBalanceException;

@Service
public class TransactionService {
	
	@Autowired
	private BalanceRepository balanceRepository;
	
	
	public void customTransactionValidations(Transaction transaction) throws Exception {
		if (!"debit".equalsIgnoreCase(transaction.getType())) {
			return;
		}
		List<Balance> balances = balanceRepository.findByAccountId(transaction.getIdUserAccount());
		Optional<Balance> balance = Optional.ofNullable(balances.stream().findFirst().orElse(null));
		if (balance.isPresent()) {
			checkNegativeBalnce(balance.get(), transaction);
		}
    }
	

	private void checkNegativeBalnce(Balance balance, Transaction transaction) throws Exception {
		if (balance.getTotal().subtract(transaction.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
			throw new NegativeBalanceException(HttpStatus.BAD_REQUEST, "Balnce can't reach negative values");
		}
		return;
	}

	
	public void refreshBlance(Transaction transaction) {
		List<Balance> balances = balanceRepository.findByAccountId(transaction.getIdUserAccount());
		Optional<Balance> balance = Optional.ofNullable(balances.stream().findFirst().orElse(null));
		balance.ifPresent(b -> {
			refreshTotal(transaction, b);
			balanceRepository.save(b);
		});
	}

	
	private void refreshTotal(Transaction transaction, Balance b) {
		if ("debit".equalsIgnoreCase(transaction.getType())) {
			b.setTotal(b.getTotal().subtract(transaction.getAmount()));
		}
		if ("credit".equalsIgnoreCase(transaction.getType())) {
			b.setTotal(b.getTotal().add(transaction.getAmount()));
		}
	} 

}

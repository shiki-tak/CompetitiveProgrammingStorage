package imitatedchain.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imitatedchain.domain.model.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, String> {
	public Transactions findByTxHash(String txHash);
}

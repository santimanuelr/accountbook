package com.santimanuelr.accountbook.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santimanuelr.accountbook.domain.Balance;

/**
 * Spring Data MongoDB repository for the Balance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BalanceRepository extends MongoRepository<Balance, String> {

	List<Balance> findByAccountId(String idUserAccount);

}

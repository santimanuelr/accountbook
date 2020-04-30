package com.santimanuelr.accountbook.repository;

import com.santimanuelr.accountbook.domain.Balance;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Balance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BalanceRepository extends MongoRepository<Balance, String> {
}

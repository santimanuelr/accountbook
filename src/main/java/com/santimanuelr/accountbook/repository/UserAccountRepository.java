package com.santimanuelr.accountbook.repository;

import com.santimanuelr.accountbook.domain.UserAccount;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the UserAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
}

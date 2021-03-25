package com.fetchrewards.demo.repository;

import com.fetchrewards.demo.models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository
    extends CrudRepository<Transaction, Long>
{
}

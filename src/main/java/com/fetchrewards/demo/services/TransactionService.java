package com.fetchrewards.demo.services;


import com.fetchrewards.demo.models.Transaction;

import java.util.List;

public interface TransactionService
{
    List<Transaction> findAllTrans();

    Transaction save(long payerid, Transaction transaction);
}

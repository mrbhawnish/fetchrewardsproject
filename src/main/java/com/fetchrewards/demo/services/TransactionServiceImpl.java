package com.fetchrewards.demo.services;

import com.fetchrewards.demo.exceptions.ResourceNotFoundException;
import com.fetchrewards.demo.models.Payer;
import com.fetchrewards.demo.models.Transaction;
import com.fetchrewards.demo.repository.PayerRepository;
import com.fetchrewards.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "transactionService")
public class TransactionServiceImpl
    implements TransactionService
{
    @Autowired
    TransactionRepository transRepos;

    @Autowired
    PayerRepository payerRepos;

    @Override
    public List<Transaction> findAllTrans()
    {
        List<Transaction> transList = new ArrayList<>();
        transRepos.findAll().iterator().forEachRemaining(transList::add);

        return transList;
    }

    @Override
    public void save(long payerid, Transaction transaction)
    {
        Payer currentPayer = payerRepos.findById(payerid).orElseThrow(() -> new ResourceNotFoundException("Sorry the payer with id " + payerid + " Not Found."));

        Transaction newTransaction = new Transaction(transaction.getPoints(), currentPayer);

        transRepos.save(newTransaction);

    }


}

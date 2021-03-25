package com.fetchrewards.demo.services;

import com.fetchrewards.demo.models.Payer;
import com.fetchrewards.demo.repository.PayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "payerService")
@Transactional
public class PayerServiceImpl implements PayerService
{
    @Autowired
    PayerRepository payerrepos;

    @Override
    public List<Payer> allPayers()
    {
        List<Payer> payerList = new ArrayList<>();
        payerrepos.findAll().iterator().forEachRemaining(payerList::add);

        return payerList;
    }

    @Override
    public void save(Payer payer)
    {

      Payer currentPayer =  payerrepos.findByPayername(payer.getPayername());

      if(currentPayer.getPayername() == payer.getPayername())
      {
          throw new IllegalStateException("Sorry payer already exists");
      }
        Payer newPayer = new Payer();

        newPayer.setPayername(payer.getPayername());

        payerrepos.save(newPayer);
    }
}

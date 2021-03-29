package com.fetchrewards.demo.services;

import com.fetchrewards.demo.models.Payer;
import com.fetchrewards.demo.repository.PayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

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
    public Payer save(Payer payer)
    {

      Payer currentPayer =  payerrepos.findByPayername(payer.getPayername());

      if(currentPayer != null)
      {
          throw new ResourceAccessException("Sorry payer already exists");
      }
        Payer newPayer = new Payer();

        newPayer.setPayername(payer.getPayername());

       return payerrepos.save(newPayer);
    }
}

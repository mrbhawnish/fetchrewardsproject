package com.fetchrewards.demo.services;


import com.fetchrewards.demo.models.Payer;

import java.util.List;

public interface PayerService
{
    List<Payer> allPayers();
    Payer save(Payer payer);
}

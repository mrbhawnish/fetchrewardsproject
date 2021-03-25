package com.fetchrewards.demo.repository;

import com.fetchrewards.demo.models.Payer;
import org.springframework.data.repository.CrudRepository;

public interface PayerRepository
    extends CrudRepository<Payer, Long>
{
    Payer findByPayername(String payername);
}

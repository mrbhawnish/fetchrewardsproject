package com.fetchrewards.demo.controllers;


import com.fetchrewards.demo.models.Payer;
import com.fetchrewards.demo.models.Transaction;
import com.fetchrewards.demo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class TransactionController
{
    @Autowired
    TransactionService transService;

    @PostMapping(value="{payer/{payerid}/newtransaction",
        consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<?> addTransaction(@PathVariable long payerid,
                                            @Valid
                                            @RequestBody
                                                Transaction newTrans)
    {
        newTrans.setTransactionid(0);
        newTrans = transService.save(payerid, newTrans);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPayerURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("{payerid}")
            .buildAndExpand(newTrans.getPayer().getPayerid())
            .toUri();
        responseHeaders.setLocation(newPayerURI);
        return new ResponseEntity<>(newTrans, responseHeaders, HttpStatus.CREATED);
    }
}

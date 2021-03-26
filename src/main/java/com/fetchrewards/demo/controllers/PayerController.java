package com.fetchrewards.demo.controllers;

import com.fetchrewards.demo.models.Payer;
import com.fetchrewards.demo.repository.PayerRepository;
import com.fetchrewards.demo.services.PayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PayerController
{
    @Autowired
    PayerService payerService;

    @GetMapping(value = "/allpayers",
        produces = {"application/json"})
    public ResponseEntity<?> getAllPayers()
    {

        List<Payer> payersList = payerService.allPayers();
        return new ResponseEntity<>(payersList, HttpStatus.OK);
    }

    @PostMapping(value="/newpayer",
        consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<?> createNewPayer(@Valid @RequestBody Payer newPayer)
    {
        newPayer.setPayerid(0);
        newPayer = payerService.save(newPayer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPayerURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("{payerid}")
            .buildAndExpand(newPayer.getPayerid())
            .toUri();
        responseHeaders.setLocation(newPayerURI);
        return new ResponseEntity<>(newPayer, responseHeaders, HttpStatus.CREATED);
    }

}

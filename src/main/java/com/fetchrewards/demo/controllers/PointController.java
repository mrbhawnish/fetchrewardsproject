package com.fetchrewards.demo.controllers;


import com.fetchrewards.demo.models.Payer;
import com.fetchrewards.demo.models.Point;
import com.fetchrewards.demo.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class PointController
{
    @Autowired
    PointService pointService;

    @PostMapping(value = "/spendpoints", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> spendPoints(@Valid @RequestBody
                                         Point points)
    {
        Set<Payer> newPayers =  new HashSet<>();
        points.setPointid(0);
        newPayers = pointService.save(points);
        return new ResponseEntity<>(newPayers, null, HttpStatus.CREATED);
    }
}

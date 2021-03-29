package com.fetchrewards.demo.services;


import com.fetchrewards.demo.models.Payer;
import com.fetchrewards.demo.models.Point;
import com.fetchrewards.demo.models.Transaction;
import com.fetchrewards.demo.repository.PayerRepository;
import com.fetchrewards.demo.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Transactional
@Service(value = "pointService")
public class PointServiceImpl implements PointService
{
    @Autowired
    PointRepository pointRepos;

    @Autowired
    PayerRepository payerRepos;

    @Override
    public Map<String, Integer> save(Point points)
    {
        List<Payer> payerList = new ArrayList<>();

        payerRepos.findAll()
            .iterator()
            .forEachRemaining(payerList::add);

        // Sort the payerList depending on the timestamp
        Collections.sort(payerList,  (c1, c2) -> {
            if (c1.getCreatedDateTime().isBefore(c2.getCreatedDateTime()))
                return -1;
            else return 1;
        });

        int currentPoints = points.getPoints();

        Map<String, Integer> spenderMap = new HashMap<>();


        while (currentPoints > 0)
        {
            int randomInt = ThreadLocalRandom.current().nextInt(0,
                currentPoints + 1);


            if(randomInt == 0){
                return spenderMap;
            }

            for (Payer payer : payerList) {
                int reducedPoints = payer.getPoints() - randomInt;
                System.out.println("Initial payer points: " + payer.getPoints());
                // if our payer can afford to spend the points, then we should spend
                // otherwise, continue on to the next player
                if (reducedPoints >= 0 && currentPoints - randomInt >= 0) {
                    currentPoints -= randomInt; // decrease our currentPoints to reflect the points spent
                    System.out.println("currentPoint " + currentPoints);
                    payer.setPoints(reducedPoints); // update this player's points to reflect points spent
                    payerRepos.save(payer); // save the player to update in DB
                    // if this payer has spent before, we'll get whatever their spent amount is
                    // if they have not spent before, we will default to 0 (since they haven't yet spent money)
                    Integer existingPointsSpent = spenderMap.getOrDefault(payer.getPayername(),0);
                    // now we're going to update the entry at that key by subtracting our randomInt from
                    // whatever was at that key previously
                    spenderMap.put(payer.getPayername(), existingPointsSpent-randomInt);
                }
            }
        }

        return spenderMap;
    }

}

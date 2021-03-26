package com.fetchrewards.demo.services;


import com.fetchrewards.demo.models.Payer;
import com.fetchrewards.demo.models.Point;
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
    public Set<Payer> save(Point points)
    {
        List<Payer> payerList = new ArrayList<>();
        int currentPoints = points.getPoints();
        payerRepos.findAll()
            .iterator()
            .forEachRemaining(payerList::add);
        int randomWithThreadLocalRandomInARange = ThreadLocalRandom.current()
            .nextInt(0,
                currentPoints);
        Set<Payer> reducedPayerPo = new HashSet<>();
        while (currentPoints >= 0 && currentPoints != 0)
        {
            if(randomWithThreadLocalRandomInARange == 0){
                return reducedPayerPo;
            }
            randomWithThreadLocalRandomInARange = ThreadLocalRandom.current()
                .nextInt(0,
                    currentPoints + 1);

            for (int i = 0; i < payerList.size(); i++)
            {

                if (payerList.get(i)
                    .getPoints() - randomWithThreadLocalRandomInARange >= 0)
                {
                    int reducedPoints = payerList.get(i)
                        .getPoints() - randomWithThreadLocalRandomInARange;
                    currentPoints = currentPoints - randomWithThreadLocalRandomInARange;
                    payerList.get(i)
                        .setPoints(reducedPoints);
                    payerRepos.save(payerList.get(i));
                    Payer newPayer = new Payer(payerList.get(i).getPayername(), randomWithThreadLocalRandomInARange);
                    if(!reducedPayerPo.contains(newPayer))
                    {
                        reducedPayerPo.add(newPayer);
                    }
                }

            }
        }
        return reducedPayerPo;
    }
}

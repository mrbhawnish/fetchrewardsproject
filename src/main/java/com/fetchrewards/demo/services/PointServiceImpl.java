package com.fetchrewards.demo.services;


import com.fetchrewards.demo.models.Point;
import com.fetchrewards.demo.repository.PayerRepository;
import com.fetchrewards.demo.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "pointService")
public class PointServiceImpl implements PointService
{
    @Autowired
    PointRepository pointRepos;

    @Autowired
    PayerRepository payerRepos;

    @Override
    public void save(Point points)
    {

    }
}

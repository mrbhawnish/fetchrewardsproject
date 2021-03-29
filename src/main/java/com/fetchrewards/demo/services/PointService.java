package com.fetchrewards.demo.services;

import com.fetchrewards.demo.models.Payer;
import com.fetchrewards.demo.models.Point;

import java.util.Map;
import java.util.Set;

public interface PointService
{
   Map<String, Integer> save(Point points);
}

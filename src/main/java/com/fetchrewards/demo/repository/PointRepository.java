package com.fetchrewards.demo.repository;

import com.fetchrewards.demo.models.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository
    extends CrudRepository<Point, Long>
{
}

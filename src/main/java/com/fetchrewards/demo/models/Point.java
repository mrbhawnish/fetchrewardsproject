package com.fetchrewards.demo.models;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "points")
public class Point
{
    @Transient
    private int points;

    public Point()
    {
    }

    public Point(int points)
    {
        this.points = points;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }
}

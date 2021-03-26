package com.fetchrewards.demo.models;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


@Entity
@Table(name = "points")
public class Point
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pointid;

    private int points;

    public Point()
    {
    }

    public Point(int points)
    {
        this.points = points;
    }

    public long getPointid()
    {
        return pointid;
    }

    public void setPointid(long pointid)
    {
        this.pointid = pointid;
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

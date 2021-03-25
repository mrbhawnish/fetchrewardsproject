package com.fetchrewards.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionid;

    private int points;

    @ManyToOne
    @JoinColumn(name = "payerid", nullable = false)
    @JsonIgnoreProperties(value = "transactions")
    private Payer payer;

    public Transaction()
    {
    }

    public Transaction(
        int points,
        Payer payer)
    {
        this.points = points;
        this.payer = payer;
    }

    public long getTransactionid()
    {
        return transactionid;
    }

    public void setTransactionid(long transactionid)
    {
        this.transactionid = transactionid;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public Payer getPayer()
    {
        return payer;
    }

    public void setPayer(Payer payer)
    {
        this.payer = payer;
    }
}

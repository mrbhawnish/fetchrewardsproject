package com.fetchrewards.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payers")
public class Payer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long payerid;

    @Column(unique = true)
    private String payername;

    private int points;

    @OneToMany(mappedBy = "payer",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "payer",
    allowSetters = true)
    private List<Transaction> transactions = new ArrayList<>();

    public Payer()
    {
    }

    public Payer(
        String payername,
        int points)
    {
        this.payername = payername;
        this.points = points;
    }

    public long getPayerid()
    {
        return payerid;
    }

    public void setPayerid(long payerid)
    {
        this.payerid = payerid;
    }

    public String getPayername()
    {
        return payername;
    }

    public void setPayername(String payername)
    {
        this.payername = payername;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public List<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions)
    {
        this.transactions = transactions;
    }
}

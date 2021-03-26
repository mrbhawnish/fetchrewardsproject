package com.fetchrewards.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionid;

    private int points;

    @CreationTimestamp
    private LocalDateTime createDateTime;

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

    public LocalDateTime getCreateDateTime()
    {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime)
    {
        this.createDateTime = createDateTime;
    }
}

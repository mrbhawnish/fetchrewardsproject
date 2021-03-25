package com.fetchrewards.demo.exceptions;

public class ResourceNotFoundException
    extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super("Error From Fetch Rewards Application " + message);
    }
}

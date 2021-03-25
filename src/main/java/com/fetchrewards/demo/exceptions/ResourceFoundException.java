package com.fetchrewards.demo.exceptions;

public class ResourceFoundException
    extends RuntimeException
{
    public ResourceFoundException(String message)
    {
        super("Error from Fetch Rewards Application " + message);
    }
}

package com.fetchrewards.demo.services;

import com.fetchrewards.demo.models.ValidationError;

import java.util.List;

public interface HelperFunctions
{

    List<ValidationError> getConstraintViolation(Throwable cause);

}

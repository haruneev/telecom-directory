package com.telecom.commonExceptions;

// Exception class used when customer is not found
public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}

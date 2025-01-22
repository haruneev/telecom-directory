package com.telecom.commonExceptions;

/**
 * // Exception class used when phone number is not found
 */
public class PhoneNumberNotFoundException extends RuntimeException{
    public PhoneNumberNotFoundException(String message) {
        super(message);
    }
}

package com.telecom.controller;

import com.telecom.commonExceptions.CustomerNotFoundException;
import com.telecom.commonExceptions.PhoneNumberNotFoundException;
import com.telecom.services.TelecomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This provides REST API endpoints to get all phone numbers , get phone numbers for a specific customer and to
 * activate a phone number
 */
@RestController
public class TelecomController {

    @Autowired
    TelecomService telecomService;

    @GetMapping("/")
    public ResponseEntity<String> getRootEndPoint(){
        return ResponseEntity.ok("Telecom API - Has endpoints to get all phone numbers , get phone numbers by " +
                "customer and to activate a phone number");
    }

    /**
     * API Endpoint to get all phone numbers.
     * @return Returns response containing a list of all phone numbers.
     */
    @GetMapping("/phoneNumbers")
    public ResponseEntity<List<String>> getAllPhoneNumbers() {
        try {
            List<String> allPhoneNumbers = telecomService.getAllPhoneNumbers();
            return ResponseEntity.ok(allPhoneNumbers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(List.of("Error: " + e.getMessage()));
        }
    }



    /**
     *  API Endpoint to get phone numbers of a particular customer
     * @param customer Name of the customer for whom we need to get the phone numbers
     * @return Returns response containing list of phone numbers associated with the customer or returns response
     * with an error message
     */
    @GetMapping("/phoneNumbers/{customer}")
    public ResponseEntity<List<String>> getPhoneNumbersByCustomer(@PathVariable String customer) {
        try {
            List<String> customerPhoneNumbers = telecomService.getAllPhoneNumbersByCustomer(customer);
            return ResponseEntity.ok(customerPhoneNumbers);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(404).body(List.of(e.getMessage()));
        } catch (Exception exception) {
            return ResponseEntity.status(500).body(List.of("Error: " + exception.getMessage()));
        }
    }


    /**
     * API Endpoint to activate a number
     * @param phoneNumber The phone number that needs to be activated
     * @return Returns the response with a message whether the number is activated successfully
     */
    @PostMapping(path = "/activate/{phoneNumber}")
    public ResponseEntity<String> activateNumber(@PathVariable String phoneNumber) {
        try {
            String response = telecomService.activateNumber(phoneNumber);
            return ResponseEntity.ok(response);
        } catch (PhoneNumberNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(500).body("Error: " + exception.getMessage());
        }
    }

}


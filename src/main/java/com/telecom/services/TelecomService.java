package com.telecom.services;

import com.telecom.commonExceptions.CustomerNotFoundException;
import com.telecom.commonExceptions.PhoneNumberNotFoundException;
import com.telecom.models.PhoneNumber;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This has methods to get and activate phone numbers .It uses the data initialized in the bean in
 * class TelecomInitialDataProviderConfig.
 */
@Service
public class TelecomService {

    public final Map<String, Set<PhoneNumber>> phoneNumbers;

    public TelecomService(Map<String, Set<PhoneNumber>> customerPhoneNumbers) {
        this.phoneNumbers = customerPhoneNumbers;
    }

    /**
     * Gets all phone numbers in the system
     * @return Returns a list of all phone numbers
     */
    public List<String> getAllPhoneNumbers() {
        try {
            return phoneNumbers.values().stream()
                    .flatMap(Set::stream)
                    .map(PhoneNumber::getNumber)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Gets all phone numbers associated with a specific customer
     * @param customer Name of the customer
     * @return Returns all the phone numbers associated with the customer
     */
    public List<String> getAllPhoneNumbersByCustomer(String customer) {
        try {
            List<String> customerPhoneNumbers = phoneNumbers.getOrDefault(customer, Collections.emptySet()).stream()
                    .map(PhoneNumber::getNumber)
                    .collect(Collectors.toList());
            if (!(customerPhoneNumbers.size() > 0)) {
                throw new CustomerNotFoundException("Customer is not in the system");
            }
            return customerPhoneNumbers;
        } catch (CustomerNotFoundException customerNotFoundException) {
            throw customerNotFoundException;
        } catch (Exception exception) {
            throw exception;
        }
    }

    /**
     * Activates the phone number if it is in the system .
     * @param phoneNumber The number that needs to be activated
     * @return Returns a message whether the number is activated or not
     */
    public String activateNumber(String phoneNumber) {
        try {

            Optional<PhoneNumber> phoneNumberObjectToActivate = phoneNumbers.values().stream()
                    .flatMap(Set::stream)
                    .filter(number -> number.getNumber().equals(phoneNumber))
                    .findFirst();
            if (phoneNumberObjectToActivate.isEmpty()) {
                throw new PhoneNumberNotFoundException("Phone number not found");
            }

            if (!phoneNumberObjectToActivate.get().isActivated()) {
                phoneNumberObjectToActivate.get().setActivated(true);
                return "Phone number is activated";
            } else {
                return "Phone number is already activated";
            }
        } catch (PhoneNumberNotFoundException phoneNumberNotFoundException) {
            throw phoneNumberNotFoundException;
        } catch (Exception exception) {
            throw exception;
        }

    }
}

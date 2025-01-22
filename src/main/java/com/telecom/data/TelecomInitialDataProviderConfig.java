package com.telecom.data;

import com.telecom.models.PhoneNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class TelecomInitialDataProviderConfig {

   
    @Bean
    public Map<String, Set<PhoneNumber>> customerPhoneNumbers() {
        Map<String, Set<PhoneNumber>> phoneNumbers = new HashMap<>();


        phoneNumbers.putIfAbsent("Alex", new HashSet<>());
        phoneNumbers.get("Alex").add(new PhoneNumber("0987654390"));
        phoneNumbers.get("Alex").add(new PhoneNumber("0127654391"));
        phoneNumbers.get("Alex").add(new PhoneNumber("0137654392"));

        phoneNumbers.putIfAbsent("George", new HashSet<>());
        phoneNumbers.get("George").add(new PhoneNumber("0988888399"));
        phoneNumbers.get("George").add(new PhoneNumber("0127684400"));

        phoneNumbers.putIfAbsent("Fabio", new HashSet<>());
        phoneNumbers.get("Fabio").add(new PhoneNumber("1122334455"));

        return phoneNumbers;

    }
}

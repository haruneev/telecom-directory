package com.telecom;

import com.telecom.commonExceptions.PhoneNumberNotFoundException;
import com.telecom.models.PhoneNumber;
import com.telecom.services.TelecomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TelecomServiceTest {
    private TelecomService telecomService;

    private Map<String, Set<PhoneNumber>> mockPhoneNumbers;

    @BeforeEach
    void setUp() {
        mockPhoneNumbers = mock(Map.class);
        telecomService = new TelecomService(mockPhoneNumbers);

    }

    /**
     * Test to check whether the service retrieves all phone numbers
     */

    @Test
    public void testGetAllPhoneNumbers() {
        // Arrange
        Set<PhoneNumber> AlexPhoneNumbers = Set.of(
                new PhoneNumber("0987654399"),
                new PhoneNumber("0127654399")
        );

        Set<PhoneNumber> GeorgePhoneNumbers = Set.of(
                new PhoneNumber("0988888399")
        );
        // Mock
        when(mockPhoneNumbers.values()).thenReturn(List.of(AlexPhoneNumbers, GeorgePhoneNumbers));

        // Act
        List<String> allPhoneNumbers = telecomService.getAllPhoneNumbers();

        // Assert
        assertEquals(3, allPhoneNumbers.size());
        assertTrue(allPhoneNumbers.contains("0987654399"));
        assertTrue(allPhoneNumbers.contains("0988888399"));

    }

    /**
     * This tests whether service gets all phone numbers of a customer when a customer name is passed
     */

    @Test
    public void testGetAllPhoneNumbersByCustomer() {
        // Arrange
        Set<PhoneNumber> testCustomerNumbers = Set.of(
                new PhoneNumber("0987654399"),
                new PhoneNumber("0127654399")
        );
        //Mock
        when(mockPhoneNumbers.getOrDefault("testCustomer", Collections.emptySet())).thenReturn(testCustomerNumbers);

        //Act
        List<String> numbers = telecomService.getAllPhoneNumbersByCustomer("testCustomer");

        // Assert
        assertEquals(2, numbers.size());
        assertTrue(numbers.contains("0987654399"));
        assertTrue(numbers.contains("0127654399"));

    }
    /**
     * This tests whether service activates a phone number successfully , given that the phone number exists in the
     * system
     */
    @Test
    public void testActivateNumber_Success() {

        // Arrange
        Set<PhoneNumber> AlexPhoneNumbers = Set.of(
                new PhoneNumber("0987654399"),
                new PhoneNumber("0127654399")
        );

        Set<PhoneNumber> GeorgePhoneNumbers = Set.of(
                new PhoneNumber("0988888399")
        );
        // Mock
        when(mockPhoneNumbers.values()).thenReturn(List.of(AlexPhoneNumbers, GeorgePhoneNumbers));

        // Act
        String result = telecomService.activateNumber("0988888399");

        // Assert
        assertEquals("Phone number is activated", result);

    }

    /**
     * This tests whether the service throws exception while attempting to activate a phone number that does not
     * exist in the system
     */
    @Test
    public void testActivateNumber_NotFoundThrowsException() {

        // Arrange
        Set<PhoneNumber> AlexPhoneNumbers = Set.of(
                new PhoneNumber("0987654399"),
                new PhoneNumber("0127654399")
        );
        Set<PhoneNumber> GeorgePhoneNumbers = Set.of(
                new PhoneNumber("0988888399")
        );
        // Mock
        when(mockPhoneNumbers.values()).thenReturn(List.of(AlexPhoneNumbers, GeorgePhoneNumbers));
        // Act and Assert
        assertThrows(PhoneNumberNotFoundException.class, () -> telecomService.activateNumber("099999999"));

    }

    /**
     * This tests whether the number is already activated before attempting to activate it.
     */
    @Test
    public void testActivateNumber_AlreadyActivated() {

        PhoneNumber number1 = new PhoneNumber("0987654399");
        number1.setActivated(true);
        PhoneNumber number2 = new PhoneNumber("0127654399");

        Set<PhoneNumber> AlexPhoneNumbers = Set.of(
                number1,
                number2
        );
        // Mock
        when(mockPhoneNumbers.values()).thenReturn(List.of(AlexPhoneNumbers));
        // Act
        String result = telecomService.activateNumber("0987654399");
        // Assert
        assertEquals("Phone number is already activated", result);

    }

}


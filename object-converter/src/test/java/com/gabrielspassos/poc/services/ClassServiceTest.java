package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.dto.AccountDTO;
import com.gabrielspassos.poc.dto.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassServiceTest {

    private ClassService classService;

    @BeforeEach
    void initService() {
        classService = ClassService.getClassService();
    }

    @Test
    void shouldReturnAttributeNamesAndValues() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        Map<String, Object> attributesNamesAndValuesFromObject
                = classService.getAttributesNamesAndValuesFromObject(account);

        assertFalse(attributesNamesAndValuesFromObject.isEmpty());
        assertTrue(attributesNamesAndValuesFromObject.containsKey("agency"));
        assertEquals("0001", attributesNamesAndValuesFromObject.get("agency"));

        assertTrue(attributesNamesAndValuesFromObject.containsKey("number"));
        assertEquals("12345", attributesNamesAndValuesFromObject.get("number"));

        assertTrue(attributesNamesAndValuesFromObject.containsKey("digit"));
        assertEquals(6L, attributesNamesAndValuesFromObject.get("digit"));
    }

    @Test
    void shouldReturnField() throws NoSuchFieldException {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setContractNumber(23232L);

        Field accessibleFieldByName = classService.getAccessibleFieldByName(employeeDTO, "contractNumber");

        assertNotNull(accessibleFieldByName);
        assertEquals("contractNumber", accessibleFieldByName.getName());
    }

    @Test
    void shouldThrowErrorForNonExistentField() {
        AccountDTO accountDTO = new AccountDTO();

        assertThrows(NoSuchFieldException.class, () -> classService.getAccessibleFieldByName(accountDTO, "abc"));
    }

}
package com.sicred.poc.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExceptionControllerTest {

    @InjectMocks
    private ExceptionController controller;
    @Mock
    private RuntimeException runtimeException;
    @Mock
    private WebRequest webRequest;

    @Test
    @DisplayName("Should Pass When ResponseEntity With Error")
    void testShouldPassWhenResponseEntityWithError() {
        ResponseEntity<Object> response = controller.exceptionHandler(runtimeException, webRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(),
                "Assertion fail, request status invalid");
    }

}
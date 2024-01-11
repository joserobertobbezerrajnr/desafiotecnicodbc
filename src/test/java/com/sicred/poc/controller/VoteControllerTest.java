package com.sicred.poc.controller;

import com.sicred.poc.external.dto.VotingDTO;
import com.sicred.poc.external.dto.VotingSavedDTO;
import com.sicred.poc.service.impl.VoteService;
import com.sicred.poc.template.AgendaTemplate;
import com.sicred.poc.template.VotingTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
class VoteControllerTest {

    @InjectMocks
    private VoteController controller;
    @Mock
    private VoteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When PollResult OK")
    void testShouldPassWhenPollResultOK() {
        ResponseEntity<VotingDTO> response = controller.pollResult(AgendaTemplate.validAgendaDTO());
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When Voting OK")
    void testShouldPassWhenVotingOK() {
        VotingSavedDTO votingSavedDTO = VotingTemplate.validVotingSavedDTO();
        ResponseEntity<Object> response = controller.voting(votingSavedDTO);
        verify(service, atMostOnce()).voting(votingSavedDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

}
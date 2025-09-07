package com.triminds.factcheck.controller;

import com.triminds.factcheck.model.ResponseEvaluation;
import com.triminds.factcheck.service.OrchestrationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VerificationController.class)
class VerificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrchestrationService orchestrationService;

    @Test
    void verifyEndpoint_returnsStatus200() throws Exception {
        when(orchestrationService.verifyText(anyString())).thenReturn(new ResponseEvaluation());

        mockMvc.perform(post("/verify")
                .content("\"Test claim\"")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}

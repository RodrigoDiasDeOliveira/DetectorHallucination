package com.triminds.factcheck.controller;

import com.triminds.factcheck.model.ResponseEvaluation;
import com.triminds.factcheck.service.OrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class VerificationController {
    @Autowired
    private OrchestrationService orchestrationService;

    @PostMapping
    public ResponseEvaluation verify(@RequestBody String llmResponse) {
        return orchestrationService.verify(llmResponse);
    }
}
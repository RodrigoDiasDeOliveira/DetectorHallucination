package com.triminds.factcheck.controller;

import com.triminds.factcheck.model.ResponseEvaluation;
import com.triminds.factcheck.service.OrchestrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify")
public class VerificationController {

    private final OrchestrationService orchestrationService;

    public VerificationController(OrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    @PostMapping
    public ResponseEvaluation verify(@RequestBody String text) {
        return orchestrationService.verifyText(text);
    }
}

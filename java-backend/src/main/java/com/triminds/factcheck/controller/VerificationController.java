package com.triminds.factcheck.controller;

import com.triminds.factcheck.model.VerificationResult;
import com.triminds.factcheck.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;

    @GetMapping("/test")
    public String testEndpoint() {
        return "Verification Service is running!";
    }
}

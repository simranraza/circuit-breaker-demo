package com.genius.circuit_breaker_demo.controllers;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.genius.circuit_breaker_demo.services.TheUnstableService;

@RestController
public class DemoController {
    @Autowired
    private TheUnstableService theUnstableService;

    @GetMapping("/unstable")
    @CircuitBreaker(name = "theUnstableService", fallbackMethod = "fallbackResponse")
    public String unstableEndpoint() {
        return theUnstableService.generateRamdomString();
    }

    public String fallbackResponse(Throwable e) {
        // Log the exception if needed
        System.err.println("Fallback triggered due to: " + e.getMessage());
        return "Fallback response: Service is currently unavailable. Please try again later.";
    }
}

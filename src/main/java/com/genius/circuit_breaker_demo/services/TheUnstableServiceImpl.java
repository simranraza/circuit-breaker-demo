package com.genius.circuit_breaker_demo.services;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class TheUnstableServiceImpl implements TheUnstableService{

    @Override
    public String generateRamdomString() {
        
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Random failure");
        }
        return "Successfull call-" + new Random().nextInt(1000);
    }

}

package com.example.micrometerprometheusdemo;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class FruitController {

    private final FruitService fruitService;
    private final MeterRegistry meterRegistry;

    public FruitController(FruitService fruitService, MeterRegistry meterRegistry) {this.fruitService = fruitService;
        this.meterRegistry = meterRegistry;
    }

    @PostMapping("/fruits")
    public ResponseEntity addFruit(@RequestBody FruitRequest fruitRequest) {
        log.info("Request : {}", fruitRequest);
        if(fruitRequest.getColor().equalsIgnoreCase("green")){
            meterRegistry.counter("green-fruit-counter").increment();
        }
        if(fruitRequest.getColor().equalsIgnoreCase("red")){
            throw  new RuntimeException("Some Mock exception");
        }
        fruitService.saveFruit(fruitRequest.toFruitModel());
        meterRegistry.gauge("some-cache-size",Math.random()*100);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/fruits")
    public List<FruitModel> getAllFruit() {
        return fruitService.findAll();
    }
}

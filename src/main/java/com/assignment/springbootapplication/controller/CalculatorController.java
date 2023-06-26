package com.assignment.springbootapplication.controller;

import com.assignment.springbootapplication.service.CalculatorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CalculatorController {
    private static final Logger log = LoggerFactory.getLogger(CalculatorController.class);
    @Autowired
    private CalculatorServiceImpl calculatorServiceImpl;

    @PostMapping("/addition")
    public ResponseEntity<String> performAddition(@RequestParam(value = "num1") int num1,
                                                  @RequestParam(value = "num2") int num2) {
        log.info("Performing addition: num1={}, num2={}", num1, num2);
        int result = calculatorServiceImpl.performAddition(num1, num2);
        log.info("Addition result: {}", result);
        return ResponseEntity.ok("Result: " + result);
    }

    @PostMapping("/perform")
    public ResponseEntity<Integer> performSubtraction(@RequestParam("num1") int num1,
                                                      @RequestParam("num2") int num2) {
        log.info("Performing subtraction: num1={}, num2={}", num1, num2);
        int result = calculatorServiceImpl.performSubtraction(num1, num2);
        log.info("Subtraction result: {}", result);
        return ResponseEntity.ok(result);
    }
}

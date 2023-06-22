package com.assignment.springbootapplication.controller;

import com.assignment.springbootapplication.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/addition")
    public ResponseEntity<String> performAddition(@RequestParam(value = "num1") int num1,
                                                  @RequestParam(value = "num2") int num2) {
        int result = calculatorService.performAddition(num1, num2);
        return ResponseEntity.ok("Result: " + result);
    }

    @PostMapping("/perform")
    public ResponseEntity<Integer> performSubtraction(@RequestParam("num1") int num1,
                                                      @RequestParam("num2") int num2) {
        int result = calculatorService.performSubtraction(num1, num2);
        return ResponseEntity.ok(result);
    }

}

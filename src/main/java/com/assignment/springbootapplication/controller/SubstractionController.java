package com.assignment.springbootapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubstractionController {
    @Autowired
    private SubstractionService subtractionService;

    @PostMapping("/perform")
    public ResponseEntity<Integer> performSubtraction(@RequestParam("num1") int num1,
                                                      @RequestParam("num2") int num2) {
        int result = subtractionService.performSubtraction(num1, num2);
        return ResponseEntity.ok(result);
    }
}

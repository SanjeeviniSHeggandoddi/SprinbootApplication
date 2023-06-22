package com.assignment.springbootapplication.service;

import com.assignment.springbootapplication.entity.SubstractionEntity;
import com.assignment.springbootapplication.repository.SubstractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class SubstractionService {
    @Autowired
    private SubstractionFunctionUtil subtractionFunctionUtil;
    @Autowired
    private SubstractionRepository subtractionRepository;

    @Transactional
    public int performSubtraction(int num1, int num2) {
        int result = subtractionFunctionUtil.executeSubtraction(num1, num2);
        SubstractionEntity subtractionEntity = new SubstractionEntity();
        subtractionEntity.setNum1(num1);
        subtractionEntity.setNum2(num2);
        subtractionEntity.setResult(result);
        subtractionRepository.save(subtractionEntity);

        return result;
    }
}

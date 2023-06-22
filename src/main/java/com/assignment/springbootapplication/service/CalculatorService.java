package com.assignment.springbootapplication.service;

import com.assignment.springbootapplication.entity.SubstractionEntity;
import com.assignment.springbootapplication.repository.AdditionRepository;
//import com.assignment.springbootapplication.procedure.StoredProcedureUtil;
import com.assignment.springbootapplication.procedure.StoredProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CalculatorService {
    @Autowired
    private AdditionRepository additionRepository;
    @Autowired
    private StoredProcedureService storedProcedureUtil;

    @Transactional
    public int performAddition(int num1, int num2) {
        int result = storedProcedureUtil.executeAddTwoNumbers(num1, num2);
       /* AdditionResult additionEntity = new AdditionResult();
        additionEntity.setNum1(num1);
        additionEntity.setNum2(num2);
        additionEntity.setResult(result);
        additionRepository.save(additionEntity);*/
        return result;
    }

    @Transactional
    public int performSubtraction(int num1, int num2) {
        int result = storedProcedureUtil.executeSubtraction(num1, num2);
        return result;
    }
}
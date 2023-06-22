package com.assignment.springbootapplication.service;

//import com.assignment.springbootapplication.procedure.StoredProcedureUtil;
import com.assignment.springbootapplication.procedure.StoredProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CalculatorServiceImpl implements CalculatorService{
    @Autowired
    private StoredProcedureService storedProcedureUtil;
    @Override
    @Transactional
    public int performAddition(int num1, int num2) {
        int result = storedProcedureUtil.executeAddTwoNumbers(num1, num2);
        return result;
    }
    @Override
    @Transactional
    public int performSubtraction(int num1, int num2) {
        int result = storedProcedureUtil.executeSubtraction(num1, num2);
        return result;
    }
}
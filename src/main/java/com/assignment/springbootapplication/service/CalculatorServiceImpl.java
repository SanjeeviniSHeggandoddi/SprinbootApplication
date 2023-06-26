package com.assignment.springbootapplication.service;

import com.assignment.springbootapplication.procedure.StoredProcedureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CalculatorServiceImpl implements CalculatorService{
    private static final Logger logger = LogManager.getLogger(CalculatorServiceImpl.class);

    @Autowired
    private StoredProcedureService storedProcedureUtil;

    @Override
    @Transactional
    public int performAddition(int num1, int num2) {
        logger.info("Adding {} and {}", num1, num2);
        int result = storedProcedureUtil.executeAddTwoNumbers(num1, num2);
        logger.info("Result: {}", result);
        return result;
    }

    @Override
    @Transactional
    public int performSubtraction(int num1, int num2) {
        logger.info("Subtracting {} from {}", num1, num2);
        int result = storedProcedureUtil.executeSubtraction(num1, num2);
        logger.info("Result: {}", result);
        return result;
    }
}
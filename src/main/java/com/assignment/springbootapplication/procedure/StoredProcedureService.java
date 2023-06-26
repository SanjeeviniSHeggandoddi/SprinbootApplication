package com.assignment.springbootapplication.procedure;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

@Service
public class StoredProcedureService {
    private static final Logger logger = LoggerFactory.getLogger(StoredProcedureService.class);
    @Autowired
    private EntityManager entityManager;

    public int executeAddTwoNumbers(int num1, int num2) {
        logger.info("Executing stored procedure to add two numbers: num1={}, num2={}", num1, num2);
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("ADD_TWO_NUMBERS");
        storedProcedure.registerStoredProcedureParameter("NUM1", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("NUM2", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("RESULT", Integer.class, ParameterMode.OUT);
        storedProcedure.setParameter("NUM1", num1);
        storedProcedure.setParameter("NUM2", num2);
        storedProcedure.execute();
        int result = (int) storedProcedure.getOutputParameterValue("RESULT");
        logger.info("Stored procedure execution result: {}", result);
        return result;
    }

    public int executeSubtraction(int num1, int num2) {
        logger.info("Executing subtraction query: num1={}, num2={}", num1, num2);
        Query query = entityManager.createNativeQuery("SELECT SUBTRACT_TWO_NUMBERS(:num1, :num2) FROM DUAL");
        query.setParameter("num1", num1);
        query.setParameter("num2", num2);
        int result = ((Number) query.getSingleResult()).intValue();
        logger.info("Subtraction query result: {}", result);
        return result;
    }
}

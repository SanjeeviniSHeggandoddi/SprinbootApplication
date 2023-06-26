package com.assignment.springbootapplication.util;

import com.assignment.springbootapplication.service.CalculatorServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SequenceGeneratorUtil {
    private static final Logger logger = LogManager.getLogger(SequenceGeneratorUtil.class);
    private static int counter = 0;

    public static synchronized int getIdSequence() {
        logger.info("Generating ID sequence: {}", counter + 1);
        return ++counter;
    }
}



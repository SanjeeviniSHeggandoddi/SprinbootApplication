package com.assignment.springbootapplication.util;

public class SequenceGeneratorUtil {
    private static int counter = 0;

    public static synchronized int getIdSequence() {
        return ++counter;
    }
}



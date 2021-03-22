package com.statecensusproblem;

public class CensusAnalyzerException extends Exception {
    enum ExceptionType{
        FILE_NOT_FOUND,
        WRONG_FILE_TYPE,
        WRONG_DELIMITER_TYPE,
        IO_EXCEPTION;
    }
    ExceptionType type;
    public CensusAnalyzerException(String message, ExceptionType type){
        super(message);
        this.type = type;
    }
}

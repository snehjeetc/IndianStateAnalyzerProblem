package com.statecensusproblem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class StateCodeAnalyzerTestClass {
    private CensusAnalyzer censusAnalyzer;

    private final static String INDIA_STATE_CODE_CSV_FILE_PATH
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/IndiaStateCode.csv";
    private final static String WRONG_PATH
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_FILE_TYPE
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/pom.xml";

    @Before
    public void init(){
        censusAnalyzer = new CensusAnalyzer();
    }

    @Test
    public void givenStateCodeCSVFile_ShouldReturn_TheNumberOfRecordsAfterReadingFromFileMatches() throws CensusAnalyzerException {
        int count = censusAnalyzer.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
        Assert.assertEquals(37, count);
    }

    @Test
    public void givenStateCodeCSVFile_IfGivenFileIsIncorrect_ShouldThrowACustomException(){
        try {
            ExpectedException exceptionrule = ExpectedException.none();
            exceptionrule.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadCensusData(WRONG_PATH);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_IfGivenFileIsCorrect_NotOfWrongType_ShouldThrowCustomExceptionOfWrongType() throws IOException {
        try {
            ExpectedException exceptionrule = ExpectedException.none();
            exceptionrule.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadCensusData(WRONG_FILE_TYPE);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }

}

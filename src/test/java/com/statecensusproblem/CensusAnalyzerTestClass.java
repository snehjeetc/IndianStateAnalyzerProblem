package com.statecensusproblem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class CensusAnalyzerTestClass {
    public static final String INDIA_CENSUS_CSV_FILE_PATH
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/IndianStateCensusData.csv";
    public static final String WRONG_PATH
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/test/resources/IndianStateCensusData";
    public static final String WRONG_FILE_TYPE
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/pom.xml";
    public static final String WRONG_DELIMITER_FILE
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/IndianStateCensusData.txt";

    private CensusAnalyzer censusAnalyzer;

    @Before
    public void init(){
        censusAnalyzer = new CensusAnalyzer();
    }

    @Test
    public void givenStateCensusCSVFile_ShouldReturnTrue_IfTheNumberOfRecordsAfterReadingFromFileMatches(){
        int count = 0;
        try {
            count = censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(29, count);
    }

    @Test
    public void givenStateCensusCSVFile_IfGivenFileIsIncorrect_ShouldThrowACustomException(){
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

    @Test
    public void givenCorrectStateCensusCSVFile_ButWrongDelimiterType_ShouldThrowCustomException(){
        try {
            ExpectedException exceptionrule = ExpectedException.none();
            exceptionrule.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadCensusData(WRONG_DELIMITER_FILE);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.WRONG_DELIMITER_TYPE, e.type);
        }
    }
}

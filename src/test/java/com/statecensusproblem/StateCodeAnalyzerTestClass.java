package com.statecensusproblem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static com.statecensusproblem.CensusAnalyzerTestClass.WRONG_HEADER_FILE;

public class StateCodeAnalyzerTestClass {
    private CensusAnalyzer censusAnalyzer;

    private final static String INDIA_STATE_CODE_CSV_FILE_PATH
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/IndiaStateCode.csv";
    private final static String WRONG_PATH
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_FILE_TYPE
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/pom.xml";
    public static final String WRONG_DELIMITER_FILE
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/IndiaStateCode.txt";
    @Before
    public void init(){
        censusAnalyzer = new CensusAnalyzer();
    }

    @Test
    public void givenStateCodeCSVFile_ShouldReturn_TheNumberOfRecordsAfterReadingFromFileMatches() throws CensusAnalyzerException {
        int count = censusAnalyzer.loadData(INDIA_STATE_CODE_CSV_FILE_PATH, CensusAnalyzer.DataType.STATE_CODE_TYPE);
        Assert.assertEquals(37, count);
    }

    @Test
    public void givenStateCodeCSVFile_IfGivenFileIsIncorrect_ShouldThrowACustomException(){
        try {
            ExpectedException exceptionrule = ExpectedException.none();
            exceptionrule.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadData(WRONG_PATH, CensusAnalyzer.DataType.STATE_CODE_TYPE);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStateCodeCSVFile_IfGivenFileExits_ButOfWrongType_ShouldThrowCustomExceptionOfWrongType() throws IOException {
        try {
            ExpectedException exceptionrule = ExpectedException.none();
            exceptionrule.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadData(WRONG_FILE_TYPE, CensusAnalyzer.DataType.STATE_CODE_TYPE);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenCorrectStateCodeCSVFile_ButWrongDelimiterType_ShouldThrowCustomException(){
        try {
            ExpectedException exceptionrule = ExpectedException.none();
            exceptionrule.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadData(WRONG_DELIMITER_FILE, CensusAnalyzer.DataType.STATE_CODE_TYPE);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
            System.out.println(e.type);
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.WRONG_DELIMITER_TYPE, e.type);
        }
    }

    @Test
    public void givenCorrectStateCodeCSVFile_ButWrongHeader_ShouldThrowCSV_WrongHeader_typeException(){
        try {
            ExpectedException exceptionrule = ExpectedException.none();
            exceptionrule.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadData(WRONG_HEADER_FILE, CensusAnalyzer.DataType.STATE_CODE_TYPE);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
            System.out.println(e.type);
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.WRONG_HEADER_TYPE, e.type);
        }
    }
}

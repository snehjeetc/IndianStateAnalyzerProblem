package com.statecensusproblem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CensusAnalyzerTestClass {
    public static final String INDIA_CENSUS_CSV_FILE_PATH
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/IndianStateCensusData.csv";
    public static final String WRONG_PATH
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/test/resources/IndianStateCensusData";

    private CensusAnalyzer censusAnalyzer;

    @Before
    public void init(){
        censusAnalyzer = new CensusAnalyzer();
    }

    @Test
    public void givenStateCensusCSVFile_ShouldReturnTrue_IfTheNumberOfRecordsAfterReadingFromFileMatches(){
        int count = censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, count);
    }
}

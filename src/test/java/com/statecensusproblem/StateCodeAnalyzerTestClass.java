package com.statecensusproblem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateCodeAnalyzerTestClass {
    private CensusAnalyzer censusAnalyzer;

    private final static String INDIA_STATE_CODE_CSV_FILE_PATH
            = "/home/snehjeetc12/IdeaProjects/StateCensusProblem/src/main/resources/IndiaStateCode.csv";

    @Before
    public void init(){
        censusAnalyzer = new CensusAnalyzer();
    }

    @Test
    public void givenStateCodeCSVFile_ShouldReturn_TheNumberOfRecordsAfterReadingFromFileMatches() {
        int count = censusAnalyzer.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
        Assert.assertEquals(37, count);
    }
}

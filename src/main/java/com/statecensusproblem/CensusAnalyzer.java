package com.statecensusproblem;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyzer {
    public int loadCensusData(String path) throws CensusAnalyzerException{
        int i = 0;
        try{
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CsvToBeanBuilder<StateCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<StateCensusCSV>(reader);
            csvToBeanBuilder.withType(StateCensusCSV.class)
                            .withIgnoreLeadingWhiteSpace(true);
            CsvToBean<StateCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<StateCensusCSV> censusCSVIterator = csvToBean.iterator();
            while(censusCSVIterator.hasNext()){
                StateCensusCSV stateCensusCSV = censusCSVIterator.next();
                i++;
            }
        } catch (IOException e) {
            throw new CensusAnalyzerException("File not found", CensusAnalyzerException.ExceptionType.FILE_NOT_FOUND);
        }
        return i;
    }
}

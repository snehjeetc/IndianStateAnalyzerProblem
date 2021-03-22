package com.statecensusproblem;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyzer {
    public int loadCensusData(String path) throws CensusAnalyzerException{
        int i = 0;

        Reader reader = null;
        try{
            reader = Files.newBufferedReader(Paths.get(path));
            CsvToBeanBuilder<StateCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<StateCensusCSV>(reader);
            csvToBeanBuilder.withType(StateCensusCSV.class)
                            .withIgnoreLeadingWhiteSpace(true);
            CsvToBean<StateCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<StateCensusCSV> censusCSVIterator = csvToBean.iterator();
            while(censusCSVIterator.hasNext()){
                StateCensusCSV stateCensusCSV = censusCSVIterator.next();
                i++;
            }
            reader.close();
        }catch(NoSuchFileException e){
            throw new CensusAnalyzerException("File not found", CensusAnalyzerException.ExceptionType.FILE_NOT_FOUND);
        }catch(RuntimeException e){
            throw new CensusAnalyzerException("Unable to read file", CensusAnalyzerException.ExceptionType.CORRUPT_FILE_TYPE);
        }catch(IOException e){
            throw new CensusAnalyzerException("Something went wrong", CensusAnalyzerException.ExceptionType.IO_EXCEPTION);
        }
        return i;
    }
}

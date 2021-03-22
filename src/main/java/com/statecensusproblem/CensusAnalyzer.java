package com.statecensusproblem;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {
    private final static Pattern pattern = Pattern.compile(".+\\.(txt|csv)$");

    public int loadCensusData(String path) throws CensusAnalyzerException{
        Reader reader = null;
        try{
            reader = Files.newBufferedReader(Paths.get(path));
            CsvToBeanBuilder<StateCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<StateCensusCSV>(reader);
            csvToBeanBuilder.withType(StateCensusCSV.class)
                            .withIgnoreLeadingWhiteSpace(true);
            CsvToBean<StateCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<StateCensusCSV> censusCSVIterator = csvToBean.iterator();
            Iterable<StateCensusCSV> iterable = () -> censusCSVIterator;
            reader.close();
            return (int) StreamSupport.stream(iterable.spliterator(), false).count();
        }catch(NoSuchFileException e){
            throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.FILE_NOT_FOUND);
        }catch(RuntimeException e){
            if(!pattern.matcher(path).matches())
                throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.WRONG_FILE_TYPE);
            if(e.getCause().toString().contains("CsvDataTypeMismatchException"))
                throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.WRONG_DELIMITER_TYPE);
        }catch(IOException e){
            throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.IO_EXCEPTION);
        }
        return -1;
    }
}

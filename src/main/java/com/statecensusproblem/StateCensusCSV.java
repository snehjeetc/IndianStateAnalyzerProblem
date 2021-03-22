package com.statecensusproblem;

import com.opencsv.bean.CsvBindByName;

public class StateCensusCSV {
    @CsvBindByName(column = "State")
    String name;

    @CsvBindByName(column = "Population", required = true)
    long population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    long areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm" )
    long densityPerSqKm;

    public StateCensusCSV(){

    }

    public StateCensusCSV(String name, long population, long areaInSqKm, long densityPerSqKm) {
        this.name = name;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(long areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public long getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(long densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }
}

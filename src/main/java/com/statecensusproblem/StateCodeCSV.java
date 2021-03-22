package com.statecensusproblem;

import com.opencsv.bean.CsvBindByName;

public class StateCodeCSV {

    @CsvBindByName(column = "SrNo", required = true)
    private int srno;

    @CsvBindByName(column = "StateName", required = true)
    private String stateName;

    @CsvBindByName(column = "TIN", required = true)
    private int tin;

    @CsvBindByName(column = "StateCode", required = true)
    private String stateCode;

    public StateCodeCSV(){

    }

    public int getSrno() {
        return srno;
    }

    public void setSrno(int srno) {
        this.srno = srno;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}

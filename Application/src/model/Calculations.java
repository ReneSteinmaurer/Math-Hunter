package model;

public class Calculations {
    private String calculation;
    private int result;

    public Calculations(String calculation, int result) {
        this.calculation = calculation;
        this.result = result;
    }

    public String getCalculation() {
        return calculation;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

package CalculatorServerSide;

import java.io.Serializable;

public class CalculatorData implements Serializable {
    private static final long serialVersionUID = 1L;

    private int value1;
    private int value2;
    private String operation;

    public CalculatorData(int value1, int value2, String operation) {
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
    }

    public String Operation() {
        String total;
        switch (operation) {
            case "+":
                total = String.valueOf(value1 + value2);
                break;
            case "-":
                total = String.valueOf(value1 - value2);
                break;
            case "%":
                total = String.valueOf((float) value1 / value2);
                break;
            case "*":
                total = String.valueOf(value1 * value2);
                break;
            default:
                total = "";
                break;
        }
        return total;
    }


    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}

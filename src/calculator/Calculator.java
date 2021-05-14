package calculator;

import symbol.Symbol;

public class Calculator {

    private Double firstNumber;
    private Double secondNumber;
    private String operator;

    public Calculator() {
        clear();
    }

    public void clear() {
        clearFirstNumber();
        clearSecondNumber();
        clearOperator();
    }

    public Double getFirstNumber() {
        return firstNumber;
    }

    public String getFirstNumberString() {
        String firstNumberString = firstNumber.toString();
        
        while (firstNumberString.contains(".") && firstNumberString.endsWith("0")) {
            int index = firstNumberString.lastIndexOf("0");
            firstNumberString = firstNumberString.substring(0, index);
        }
        if (firstNumberString.endsWith(".")) {
            int index = firstNumberString.indexOf(".");
            firstNumberString = firstNumberString.substring(0, index);
        }
        firstNumberString = firstNumberString.replace("-", Symbol.MINUS);

        return firstNumberString;
    }

    public void setFirstNumber(Double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        firstNumber = firstNumber.replace(Symbol.MINUS, "-");
        firstNumber = firstNumber.replaceAll(",", "");
        this.firstNumber = Double.parseDouble(firstNumber);
    }

    public void clearFirstNumber() {
        firstNumber = Double.NaN;
    }

    public Double getSecondNumber() {
        return secondNumber;
    }

    public String getSecondNumberString() {
        String secondNumberString = secondNumber.toString();
        
        while (secondNumberString.contains(".") && secondNumberString.endsWith("0")) {
            int index = secondNumberString.lastIndexOf("0");
            secondNumberString = secondNumberString.substring(0, index);
        }
        if (secondNumberString.endsWith(".")) {
            int index = secondNumberString.indexOf(".");
            secondNumberString = secondNumberString.substring(0, index);
        }
        secondNumberString = secondNumberString.replace("-", Symbol.MINUS);

        return secondNumberString;
    }

    public void setSecondNumber(Double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        secondNumber = secondNumber.replace(Symbol.MINUS, "-");
        secondNumber = secondNumber.replaceAll(",", "");
        this.secondNumber = Double.parseDouble(secondNumber);
    }

    public void clearSecondNumber() {
        secondNumber = Double.NaN;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void clearOperator() {
        operator = null;
    }

    public void addition() {
        firstNumber += secondNumber;
    }

    public void subtraction() {
        firstNumber -= secondNumber;
    }

    public void multiplication() {
        firstNumber *= secondNumber;
    }

    public void division() {
        firstNumber /= secondNumber;
    }

    public void percent() {
        if (!Double.isNaN(secondNumber)) {
            secondNumber /= 100;
        } else {
            firstNumber /= 100;
        }
    }

    public void inverse() {
        if (!Double.isNaN(secondNumber)) {
            secondNumber = 1 / secondNumber;
        } else {
            firstNumber = 1 / firstNumber;
        }
    }

    public void square() {
        if (!Double.isNaN(secondNumber)) {
            secondNumber *= secondNumber;
        } else {
            firstNumber *= firstNumber;
        }
    }

    public void squareRoot() {
        if (!Double.isNaN(secondNumber)) {
            secondNumber = Math.sqrt(secondNumber);
        } else {
            firstNumber = Math.sqrt(firstNumber);
        }
    }

    public void calculate() {
        switch (operator) {
        case Symbol.PLUS:
            addition();
            break;

        case Symbol.MINUS:
            subtraction();
            break;

        case Symbol.MULTIPLICATION:
            multiplication();
            break;

        case Symbol.DIVISION:
            division();
            break;

        case Symbol.PERCENT:
            percent();
            break;

        case Symbol.INVERSE:
            inverse();
            break;

        case Symbol.SQUARE:
            square();
            break;

        case Symbol.SQUARE_ROOT:
            squareRoot();
            break;
        }
    }

}

package calculator;

import stack.Stack;

public class Calculator {

    private Stack<Double> values;

    public Calculator() {
        values = new Stack<>();
    }

    public void saveValue(Double value) {
        values.push(value);
    }

    public Double calculate(Character operator) {

        Double fistValue = values.pop();
        Double secondValue = values.pop();
        Double result = 0.0;

        switch (operator) {
            case '*':
                result = secondValue * fistValue;
                break;
            case '/':
                result = secondValue / fistValue;
                break;
            case '-':
                result = secondValue - fistValue;
                break;
            case '+':
                result =  secondValue + fistValue;
                break;            
        }

        values.push(result);
        return result;
    }

    public Double getResult() {
        return values.pop();
    }

}
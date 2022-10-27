package calculator;

import lexer.Token;
import lexer.TokenType;
import stack.Stack;

public class Calculator {

    private Stack<Double> values;

    public Calculator() {
        values = new Stack<>();
    }

    public void saveValue(Token token) throws RuntimeException {
        if (token.type == TokenType.NUM) {
            values.push(Double.valueOf(token.lexeme));
        } else {
            throw new RuntimeException("Error: Unexpected character");
        }
    }

    public Double calculate(Token token) throws RuntimeException {

        Double fistValue = values.pop();
        Double secondValue = values.pop();
        Double result = 0.0;

        switch (token.type) {
            case MINUS:
                result = secondValue - fistValue;
                break;
            case PLUS:
                result =  secondValue + fistValue;
                break;
            case SLASH:
                result = secondValue / fistValue;
                break;
            case STAR:
                result = secondValue * fistValue;
                break;
            default:
                throw new RuntimeException("Operador Inválido");           
        }

        values.push(result);
        return result;
    }

    public Double getResult() {
        return values.pop();
    }

}
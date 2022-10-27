package Interpreter;

import java.util.HashMap;

import expressions.Expressions;

/**
 * @author Henrique Rebelo
 */
public class Interpreter implements Expressions.Visitor<Integer> {

    public final HashMap<String, String> env;

    public Interpreter(HashMap<String, String> env) {
        this.env = env;
    }

    public int interp(Expressions expressions) {
        int value = evaluate(expressions);

        return value;
    }

    @Override
    public Integer visitNumberExpressions(Expressions.Number expressions) {
        return Integer.parseInt(expressions.value);
    }

    @Override
    public Integer visitIdentifierExpressions(Expressions.Identifier expressions) {
        if (!this.env.containsKey(expressions.key)) {
            throw new InterpreterError(expressions.key + " cannot be resolved");
        }

        return Integer.parseInt(this.env.get(expressions.key));
    }

    @Override
    public Integer visitBinopExpressions(Expressions.Binop expressions) {
        int left = evaluate(expressions.left);
        int right = evaluate(expressions.right);
        int result = 0;

        switch (expressions.operator.type) {
            case PLUS:
                result = left + right;
                break;
            case MINUS:
                result = left - right;
                break;
            case SLASH:
                result = left / right;
                break;
            case STAR:
                result = left * right;
                break;
            default:
                break;
        }

        return result;
    }

    private int evaluate(Expressions expressions) {
        return expressions.accept(this);
    }
}
package expressions;

import lexer.Token;

public abstract class Expressions {

    // visitors for expressions
    public interface Visitor<T> {
        T visitNumberExpressions(Number expr);
        T visitIdentifierExpressions(Identifier expr);
        T visitBinopExpressions(Binop expr);
    }

    // Nested Expr classes here

    // Number expression
    public static class Number extends Expressions {
        public Number(String value){
            this.value = value;
        }

        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visitNumberExpressions(this);
        }

        public final String value;
    }

    // Identifier expression
    public static class Identifier extends Expressions {
        public Identifier(String key){
            this.key = key;
        }

        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visitIdentifierExpressions(this);
        }

        public final String key;
    }

    // Binop expression
    public static class Binop extends Expressions {
        public Binop(Expressions left, Expressions right, Token operator) {
            this.left = left;
            this.right = right;
            this.operator = operator;
        }

        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visitBinopExpressions(this);
        }

        public final Expressions left;
        public final Expressions right;
        public final Token operator;
    }

    public abstract <T> T accept(Visitor<T> visitor);
}
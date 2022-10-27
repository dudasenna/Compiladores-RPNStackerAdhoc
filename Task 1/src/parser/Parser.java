package parser;

import java.util.List;
import java.util.Stack;

import expressions.Expressions;
import lexer.Token;
import lexer.TokenType;

public class Parser {

    private final List<Token> tokens;
    // The internal stack used for shift-reduce parsing
    private Stack<Expressions> stack = new Stack<>();
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    //Parsing Expressions
    public Expressions parse() {
        try {
            return expression();
        } catch (java.util.EmptyStackException error) {
            throw new ParserError("incomplete binop expression");
        }
    }

    // -------------------------------------------------------------
    // HELPERS METHODS
    // -------------------------------------------------------------
    private Expressions expression() {
        while (!isAtEnd()) {
            if(this.match(TokenType.NUM)) {
                this.stack.push(this.number());
            } else if (this.match(TokenType.ID)) {
                this.stack.push(this.identifier());
            } else if(this.match(TokenType.PLUS, TokenType.MINUS,
                    TokenType.SLASH, TokenType.STAR)) {
                this.stack.push(this.binop());
            }
            this.advance();
        }
        return this.stack.pop();
    }

    private Expressions number() {
        return new Expressions.Number(peek().lexeme);
    }

    private Expressions identifier() {
        return new Expressions.Identifier(peek().lexeme);
    }

    private Expressions binop() {
        return new Expressions.Binop(this.stack.pop(), this.stack.pop(), this.peek());
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                return true;
            }
        }

        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() {
        return peek().type == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}
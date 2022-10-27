import calculator.Calculator;
import lexer.LexError;
import lexer.Regex;
import lexer.Token;
import lexer.TokenType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import expressions.Expressions;
import Interpreter.Interpreter;
import Interpreter.InterpreterError;
import lexer.LexError;
import lexer.Token;
import parser.Parser;
import parser.ParserError;

public class Main {

    private static final Interpreter interpreter = new Interpreter(new HashMap<>());
    private static boolean hasError = false;
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/eduardasenna/Documents/CIn/2022/2022.1/Compiladores/Task 1/src/Input.txt");

        Scanner scan = new Scanner(file);
        List<Token> tokens = new ArrayList<>();

        while (scan.hasNextLine()) {
            String current = scan.nextLine().trim();
            Token token;

            token = getToken(current);
            tokens.add(token);
        }

        scan.close();

        try (Scanner input = new Scanner(file)) {
            Calculator calculator = new Calculator();
            Double result;
            String temp;

            while (input.hasNextLine()) {
                temp = input.nextLine();

                Token token = tokens.remove(0);
                System.out.println(token);

                if (token.type == TokenType.NUM) {
                    calculator.saveValue(token);
                } else {
                    calculator.calculate(token);
                }
            }
            result = calculator.getResult();

            Parser parser = new Parser(tokens);
            Expressions expressions = parser.parse();

            interpreter.env.put("y", "10");
            interpreter.env.put("x", "3");

            System.out.println(interpreter.interp(expressions));
            
        } catch (LexError e) {
            error("Lex", e.getMessage());
            hasError = true;
        }
        catch (ParserError e) {
            error("Parser", e.getMessage());
            hasError = true;
        }
        catch (InterpreterError e) {
            error("Interpreter", e.getMessage());
            hasError = true;
        }
    }

    private static Token getToken(String token) {
        Token ret = null;
        if (Regex.isNum(token)) {
            ret = new Token(TokenType.NUM, token);
        } else if (Regex.isID(token)) {
            ret = new Token(TokenType.ID, token);
        } else if(Regex.isOP(token)) {
            ret = new Token(Regex.getOPTokenType(token), token);
        } else {
            throw new LexError("Unexpected character: "+token);
        }
        return ret;
    }

    private static void error(String typeError, String message) {
        System.err.println("[" + typeError + "] Error: " + message);
        hasError = true;
    }
}


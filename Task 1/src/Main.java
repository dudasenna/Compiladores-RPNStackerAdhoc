import calculator.Calculator;
import CheckNumber.CheckNumber;
import token.Token;
import token.TokenType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/eduardasenna/Documents/CIn/2022/2022.1/Compiladores/Task 1/src/Input.txt");

        Scanner scan = new Scanner(file);
        List<Token> tokens = new ArrayList<>();

        while (scan.hasNextLine()) {
            String current = scan.nextLine().trim();
            Token token;

            if (current.equals("-")) {
                token = new Token(TokenType.MINUS, current);
            } else if (current.equals("+")) {
                token = new Token(TokenType.PLUS, current);
            } else if (current.equals("/")) {
                token = new Token(TokenType.SLASH, current);
            } else if (current.equals("*")) {
                token = new Token(TokenType.STAR, current);
            } else if (CheckNumber.isNumber(current)) {
                token = new Token(TokenType.NUM, current);
            } else {
                scan.close();
                throw new RuntimeException("Error: Unexpected character: " + current);
            }
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
            System.out.println("Resultado: " + result + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}


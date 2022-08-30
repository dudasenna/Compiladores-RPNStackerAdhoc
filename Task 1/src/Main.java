import calculator.Calculator;
import CheckNumber.CheckNumber;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/eduardasenna/Documents/CIn/2022/2022.1/Compiladores/Task 1/src/Input.txt");
        try (Scanner input = new Scanner(file)) {
            Calculator calculator = new Calculator();
            Double result;
            String temp;

            while (input.hasNextLine()) {
                temp = input.nextLine();

                if (CheckNumber.isNumber(temp)) {
                    calculator.saveValue(Double.parseDouble(temp));
                } else {
                    calculator.calculate(temp.charAt(0));
                }
            }
            result = calculator.getResult();
            System.out.println("Resultado: " + result);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


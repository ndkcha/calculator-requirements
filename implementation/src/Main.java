import javax.script.ScriptException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the calculations services!");
        String code;

        do {
            System.out.println("\nPress 1 for basic calculator functionality");
            System.out.println("Press 2 for extended functionalities");
            System.out.println("Enter any other value to exit");
            System.out.print("# ");

            code = scanner.nextLine().trim();

            if (code.equalsIgnoreCase("1"))
                startArithmeticOperations(scanner);

            if (code.equalsIgnoreCase("2"))
                startExtended(scanner);
        } while (code.equalsIgnoreCase("1") || code.equalsIgnoreCase("2"));
    }

    /**
     * It executes the logic to provide interface to extended functionalities of the calculator
     * @param scanner System's scanner in order to interact with user
     */
    private static void startExtended(Scanner scanner) {
        Calculation calculation = new Calculation();
        System.out.println("\nThe extended functionalities include,\n");
        System.out.println("1. List of Other Related Constant");
        System.out.println("2. The volume of the n-dimensional ball with unit Radius");
        System.out.println("Enter any other value to exit");
        System.out.println("After the #, start inputting the expression\n");
        String input;

        do {
            System.out.print("# ");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("1")) {
                for (Map.Entry<String, String> entry : calculation.listAlmostIntegerNumbers().entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
            if (input.equalsIgnoreCase("2")) {
                System.out.println("Result: " + calculation.calculateGelfondConstant());
            }
        } while(input.equalsIgnoreCase("1") || input.equalsIgnoreCase("2"));
    }

    /**
     * It executes the logic to provider interface to the basic arithmetic calculator
     * @param scanner System's scanner in order to interact with user
     */
    private static void startArithmeticOperations(Scanner scanner) {
        Calculation calculation = new Calculation();
        System.out.println("\nArithmetic Calculator\n");
        System.out.println("Hint: To use this part of calculator, you can input the expression in a single line and press return to have it evaluated");
        System.out.println("The calculator all kinds of arithmetic operations (e.g. +, -, *, /)");
        System.out.println("For example: 4+3+6*3 input will give the answer 25.");
        System.out.println("The calculator also supports the parenthesis inside the expression.");
        System.out.println("For example: (4+3+3)/5 input will give the answer 2");
        System.out.println("The calculator also supports symbols \"e\", \"pi\" and \"g\" (where g is Gelfond's constant)");
        System.out.println("Give \"exit\" as an input to quit");
        System.out.println("After the #, start inputting the expression\n");
        String input;
        boolean isToExit = false;

        do {
            System.out.print("# ");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                isToExit = true;
                continue;
            }

            try {
                input = calculation.replaceSymbols(input);
                System.out.println("Result: " + calculation.evaluateExpression(input));
            } catch (ScriptException e) {
                System.out.println("\nOops! There is something wrong in the input.");
                System.out.println("You can input only linear expressions like 4+3+6*3 as an input.");
                System.out.println("The input has to be the perfect expressions. for example, 4+5+ is not accepted.");
                System.out.println("The calculator only supports symbols \"e\", \"pi\" and \"g\" (where g is Gelfond's constant)");
                System.out.println("After the #, start inputting the expression\n");
            }

        } while(!isToExit);


    }
}

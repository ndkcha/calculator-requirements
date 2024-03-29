import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;

class Calculation {
    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");

    /**
     * It replaces the known symbols with their values from the expression provided by the user
     *
     * @param expression expression provided by the user
     * @return the new expression with just values
     */
    String replaceSymbols(String expression) {
        expression = expression.replace("e", String.valueOf(calculateExponent()));
        expression = expression.replace("g", String.valueOf(calculateGelfondConstant()));
        expression = expression.replace("pi", String.valueOf(pi()));
        return expression;
    }

    /**
     * It calculates and returns list of almost integer numbers that can be generated from the Gelfond's constant
     *
     * @return key-value pair with name and value of the almost integer numbers
     */
    HashMap<String, String> listAlmostIntegerNumbers() {
        HashMap<String, String> list = new HashMap<>();
        list.put("Ramanujan's constant", String.valueOf(Math.pow(calculateGelfondConstant(), Math.sqrt(163))));
        list.put("e^pi - pi", String.valueOf(calculateGelfondConstant() - pi()));
        return list;
    }

    /**
     * It evaluates the expression and gives the final result.
     *
     * @param expression expression to be evaluated
     * @return the result after evaluation
     */
    String evaluateExpression(String expression) throws Exception {
        String[] content = expression.split("[-+*/]");
        if (content.length > 2)
            throw new Exception();
        double result = Double.parseDouble(content[0]);
        if (expression.split("[+]").length == 2) {
            result = Double.parseDouble(content[0]) + Double.parseDouble(content[1]);
        }
        if (expression.split("[-]").length == 2) {
            result = Double.parseDouble(content[0]) - Double.parseDouble(content[1]);
        }
        if (expression.split("[*]").length == 2) {
            result = Double.parseDouble(content[0]) * Double.parseDouble(content[1]);
        }
        if (expression.split("[/]").length == 2) {
            result = Double.parseDouble(content[0]) / Double.parseDouble(content[1]);
        }
        return String.valueOf(result);
    }

    /**
     * It calculates the Gelfond's constant
     *
     * @return the constant value of the Gelfond's constant
     */
    double calculateGelfondConstant() {
        return Math.pow(calculateExponent(), Math.PI);
    }

    private double pi() {
        double s = 3.02;

        for (int i = 0; i < 100; i++) {
            double c = 4.0 / ((i + 2.0) * (i + 3.0) * (i + 4.0));
            if (i % 2 == 0) {
                s += c;
            } else {
                s -= c;
            }
        }

        return s;
    }

    /**
     * It calculates the value of e
     *
     * @return the value of e
     */
    private double calculateExponent() {
        double e = 1.0;
        for (int i = 1; i < 30; i++) {
            if (factorial(i) != 0.0)
                e = e + 1 / (double) factorial(i);
        }
        return e;
    }

    /**
     * It calculates the n! (n-factorial)
     *
     * @param input the number to calculate the factorial of
     * @return the final output
     */
    private int factorial(int input) {
        int n = 1;
        for (int i = 1; i <= input; i++) {
            n = n * i;
        }
        return n;
    }
}

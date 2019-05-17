import java.text.NumberFormat;
import java.util.*;

public class Solver {
    public static String Solve(String input) throws IllegalArgumentException{
        List<String> rpn = toRPN(input); // to Reverse Polish notation
        Double res = Calc(rpn);
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        nf.setGroupingUsed(false); // 1,000 to 1000
        return nf.format(res);
    }

    private static List<String> toRPN(String input) throws IllegalArgumentException{
        Map<String, Integer> operators = new HashMap<>(); // String - operator, Integer - priority
        operators.put("+", 1);
        operators.put("-", 1);
        operators.put("*", 2);
        operators.put("/", 2);
        String operatorsStr = String.join("",operators.keySet());

        StringTokenizer tokenizer = new StringTokenizer(input, operatorsStr, true);
        List<String> out = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        int even = 0;
        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if(even++%2 == 0 && requiredNumber(token)) // the numbers in even positions
                out.add(token);
            else if(requiredOperator(token, operatorsStr)){ // operators in odd positions
                while (!stack.isEmpty() && operators.get(stack.getFirst()) >= operators.get(token))
                    out.add(stack.pop());
                stack.push(token);
            }
        }

        while (!stack.isEmpty())
            out.add(stack.pop());
        return out;
    }

    private static Double Calc(List<String> rpn){
        Deque<Double> stack = new ArrayDeque<>();
        for(String elem : rpn)
        try {
            stack.push(Double.valueOf(elem));
        } catch (Exception e){
            Double B = stack.pop();
            Double A = stack.pop();
            switch (elem){
                case "+":
                    stack.push(A + B);
                    break;
                case "-":
                    stack.push(A - B);
                    break;
                case "*":
                    stack.push(A * B);
                    break;
                case "/":
                    stack.push(A / B);
                    break;
            }
        }
        return stack.pop();
    }

    private static boolean requiredNumber(String token) throws IllegalArgumentException{
        Double.parseDouble(token);
        token = token.replaceAll("\\s+","");
        if(token.startsWith(".") || token.endsWith("."))
            throw new NumberFormatException(token + " is not valid double");
        return true;
    }

    private static boolean requiredOperator(String token, String operatorsStr) throws IllegalArgumentException{
        if (!operatorsStr.contains(token))
            throw new IllegalArgumentException(token + " is unsupported operation and not double");
        return true;
    }
}

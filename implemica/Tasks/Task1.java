import java.util.Scanner;

/**
 * <h1>Calculation of the number of correct parenthesis expressions</h1>
 * The program reads from the stdin the number N of brackets,
 * calculates the number of correct parenthesis expressions
 * and writes the result to stdout.
 *
 * @author Nikolay Kuksihev
 * @version 1.0
 * @since 2024-11-24
 */
public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new  Scanner(System.in);

        //Entering the number of opening and closing brackets from the keyboard
        System.out.println("N = ");
        int number = scanner.nextInt();

        //Method call for calculation of the number of correct parenthesis expressions
        long trueParentheticalExpressions = correctParentheticalExpression(number);

        //Output the number of correct bracketed expressions to stdout
        System.out.println(trueParentheticalExpressions);
    }

    /**
     * The method counts the number of correct bracket sequences
     * using an expression over binomial coefficients:
     *           2n!
     * C(n) = --------- , n - number of opening and closing brackets.
     *         n!(n+1)!
     * In order to improve performance, the expression has been simplified.
     * The denominator and numerator are simplified when factorials are expanded into multipliers.
     * Example: n = 4;
     *          (2*4)!      1*2*3*4*5*6*7*8       6*7*8
     * C(4) = ---------- = ------------------ = ---------- = 14
     *         4!(4+1)!    1*2*3*4*(1*2*3*4*5)   1*2*3*4
     *
     * @param number - number of opening and closing brackets.
     * @throws IllegalArgumentException number of opening and closing brackets cannot be negative.
     * @return long this returns the number of correct parenthesis expressions.
     * */
    public static long correctParentheticalExpression(int number){
        if(number<0){
            throw new IllegalArgumentException("Invalid number value");
        }
        if(number==0){
            return 0;
        }
        long a = 1;
        long b = 0;
        for(int i=1; i<=2*number ; ++i){
            a *= i;
            if(i==number){
                b = a;
                ++i;
                a = 1;
            }
        }

        return a/b;
    }
}

import java.math.BigInteger;

/**
 * <h1>Calculate the sum of the digits in the number</h1>
 * The Task3 program implements an application that calculates
 * the factorial and sum of digits of a number
 * and writes the result to stdout.
 *
 * @author Nikolay Kuksihev
 * @version 1.0
 * @since 2024-11-24
*/
public class Task3 {
    public static void main(String[] args) {
        BigInteger bigInteger = factorialOfNumber(100);
        BigInteger sum = sumDigitsOfNumber(bigInteger);
        System.out.println(sum);
    }

    /**
    * This method counts the sum digits of a number
    * @param number - this is the number from the calculation the sum of the digits
    * @return BigInteger this returns the sum digits of a number.
    */
    public static BigInteger sumDigitsOfNumber(BigInteger number){
        BigInteger sum = BigInteger.ZERO;
        while(!number.equals(BigInteger.ZERO)){
            sum = sum.add(number.mod(BigInteger.TEN));
            number = number.divide(BigInteger.TEN);
        }
        return sum;
    }

    /**
     * This method counts the factorial of number
     * @param number - this is the number for the calculation of the factorial.
     * @exception IllegalArgumentException It is not possible to calculate the factorial of a negative number
     *                                     or a fractional number, the factorial can only be a natural number.
     * @return BigInteger this returns the factorial of the number.
     */
    public static BigInteger factorialOfNumber(int number){
        if(number<0){
            throw new IllegalArgumentException("Invalid number value");
        }
        if (number==0){
            return BigInteger.ONE;
        }
        BigInteger factorial = BigInteger.ONE;
        for(int i=1; i<=number ; ++i){
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
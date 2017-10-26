/*
 * name : tangli
 * time : 2017/10/26
 * problem: 公式近似求某个数的平方根
 */
import java.util.*;

public class GetNearlySqrt {

	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("please enter n:");
		
		int n = input.nextInt();
		
		System.out.printf("the nearly sqrt of n is: %f\n " , getNearlySqrt(n));

	}
	public static double getNearlySqrt(int num){
		//double ans = 0;
		double lastGuess = 1;
		double nextGuess = (lastGuess + (num / lastGuess)) / 2;
		//System.out.printf(" lastGuess: %f, nextGuess: %f\n" , lastGuess , nextGuess);
		//System.out.println(Math.abs(lastGuess - nextGuess));
		while(Math.abs(lastGuess - nextGuess) > 1e-6){
			System.out.printf(" lastGuess: %f, nextGuess: %f\n" , lastGuess , nextGuess);
			lastGuess = nextGuess;
			nextGuess = (lastGuess + (num / lastGuess)) / 2;
		}
		return nextGuess;
	}
}

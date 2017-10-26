/*
 * name : tangli
 * time : 2017/10/26
 * problem: 随机输出n*n的由0，1组成的数组
 */
import java.util.*;

public class GetMatrix {

	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("please enter n:");
		
		int n = input.nextInt();
		printMatrix(n);
	}
	
	public static void printMatrix(int n){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				int tmp = (int)(Math.random()* 1000)+ 100;
				System.out.println(tmp%2);
			}
			System.out.println();
		}
	}

}

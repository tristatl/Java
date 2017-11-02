/*
 * name : tangli
 * time : 2017/11/02 14:19
 * problem : count how many times every number show
 */
import java.util.*;

public class CountNums {

	static int[] cnt = new int[105];
	public static void main(String[] args) {
		CountNum();

	}
	public static void CountNum(){
		Scanner input = new Scanner(System.in);
		System.out.println("please input numbers (end with 0):");
		int num = input.nextInt();
		while(num != 0){
			cnt[num]++;
			num = input.nextInt();
		}
		for(int i = 1; i <= 100; i++){
			if(cnt[i] > 0){
				System.out.println(i + " shows " + cnt[i] + " times");
			}
		}
	}
}

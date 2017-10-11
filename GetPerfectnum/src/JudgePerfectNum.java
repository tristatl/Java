/*
 * 找到10000以下的完全数。编写程序，找出这四个完全数。
 */

public class JudgePerfectNum {

	public static void main(String[] args) {
		System.out.println("Perfect num within 10000:");
		judge();
	}
	public static void judge() {
			for(int i = 2; i < 10001; i++) {
				int sum = 0;
				for(int j = 1; j < i; j++) {
					if(i%j == 0) {
						sum += j;
					}
						
				}
				//System.out.println(sum);
				if(i == sum)
					System.out.println(i);
			}
	
	}

}

/*
 * name: tangli
 * time: 2017/10/12
 * problem: calculate �ڼ������������ʱ�����ҵ������Ҫ�ȴ����Ҽ���õ��Ľ������ȷ��
 * 1 + 1/2 + 1/3 +������+ 1/n
 */
import java.util.Scanner;

public class GetResult {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("please input n:");
		int n = input.nextInt();
		
		double res1 = 0,res2 = 0;
		for(int i = 1; i <= n; i++) {
			res1 += 1.0/i;
		}
		for(int i = n; i >= 1; i--) {
			res2 += 1.0/i;
		}
		System.out.printf("the result from left to right : %.16f\n", res1);
		System.out.printf("the result from right to left : %.16f\n", res2);

	}
	
}

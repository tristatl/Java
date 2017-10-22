/*
 *Name: tangli
 *time: 27/10/09
 *题目： 用阶乘式求e=2.7182.....
 */


public class GetE {
	public static void main(String []args) {
		double tmp = 1, e = 1;
		for(int i = 1; i < 100001; i++) {
			tmp = tmp/i;
			e += tmp;
			if(i%10000 == 0)
				System.out.printf("%d : %.6f\n",i,e);
				//System.out.println(e);
		}
	}
}

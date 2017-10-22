/*
 * name : tangli
 * time : 2017/10/17
 * problem : 使用下面的数列可以近似计算 ：
 * pi = 4(1 - 1/3 + 1/5 - 1/7 + 1/9 -......+ 1/(2*i-1) - 1/(2*i+1))
 */
package getPI;

public class GetPI {

	public static void main(String[] args) {
		
		double res = 0;
		for(int i = 1; i <= 100000; i++) {
			if(i%2 != 0)
				res += 1.0 / (2*i-1);
			else
				res -= 1.0 / (2*i-1);
			if(i%10000 == 0)
				System.out.println(i + ":" + 4 * res);
		}
		
	}

}

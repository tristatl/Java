/*name : tangli
 *time : 2017/10/17
 *problem:
 *编写程序，让用户输入贷款总额和以年为单位的贷款期限，然后显示利率从5%到8%，每次递增1/8的过程中，每月的支付额和总偿还额。
 *如用户输入贷款额10000，贷款期限5年，则系统显示：
 *利率	月还款额	总还款额
 *5%		188.71		11322.74
 *5.125%	189.28		11357.13
 *...
 *8%		202.76		12165.83
 * 
 */
import java.util.Scanner;

public class CompareLoan {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please input the total money and time : ");
	    double money = input.nextDouble();
	    int year = input.nextInt();
	    
	    System.out.println("利率" + "    月还款额" + "     总还款额");
	    double yRate = 0;
	    for(double i = 5; i <= 8; i += 0.125) {
	    	yRate = i * 0.01;
	    	double mRate = yRate / 12;
	    	double tmp = Math.pow((1 + mRate),60);
	    	double mRetMoney = (money * mRate * tmp) / (tmp - 1);
	    	double totRetMoney = mRetMoney * year * 12;
	    	System.out.printf("%.3f%%    %.2f    %.2f\n",i,mRetMoney,totRetMoney);
	    }

	}

}


/*name : tangli
 *time : 2017/10/17
 *problem:
 *��д�������û���������ܶ������Ϊ��λ�Ĵ������ޣ�Ȼ����ʾ���ʴ�5%��8%��ÿ�ε���1/8�Ĺ����У�ÿ�µ�֧������ܳ����
 *���û���������10000����������5�꣬��ϵͳ��ʾ��
 *����	�»����	�ܻ����
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
	    
	    System.out.println("����" + "    �»����" + "     �ܻ����");
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


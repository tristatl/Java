/*
 * 1.��ʾÿ�µ�һ�������ڼ�
 * ��д������ʾ�û�������ݺʹ�������һ�������ڼ������֣�Ȼ���ڿ���̨����ʾ����ÿ�µ�һ������ڡ�
 * ���磬����û�����������2005�ʹ���2005��1��1��Ϊ��������6��
 * ����Ӧ����ʾ2005��ÿ�µ�һ�������ڼ���ע�⣺0��ʾ�����죩��
 */

import java.util.Scanner;

public class GetWeekday {

	public static void main(String[] args) {
		int month[]= {31,28,31,30,31,30,31,31,30,31,30,31};
		
		Scanner input = new Scanner(System.in);
		System.out.println("Print input the year and the first day:");
		int year = input.nextInt();
		int num = input.nextInt();
		if(year%400 == 0 ||(year%100 != 0 && year%4 == 0))
			month[1] = 29;
		else
			month[1] = 28;
		for(int i = 0; i < 12; i++) {
			System.out.printf("month:%d  the first day:%d\n",i+1,num);
			num =(num + month[i]) % 7;
		}
		
	}

}

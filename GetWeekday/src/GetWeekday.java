/*
 * 1.显示每月第一天是星期几
 * 编写程序，提示用户输入年份和代表该年第一天是星期几的数字，然后在控制台上显示该年每月第一天的星期。
 * 例如，如果用户输入的年份是2005和代表2005年1月1日为星期六的6，
 * 程序应该显示2005年每月第一天是星期几（注意：0表示星期天）。
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

/*
 * name : tangli
 * time : 2017/11/02 14:20
 * problem : count average and numbers bigger/smaller than it
 */
import java.util.Scanner;

public class averScores {

	static int[] score = new int[10000];
	public static void main(String[] args) {
		AverScores();

	}
	public static void AverScores(){
		Scanner input = new Scanner(System.in);
		System.out.println("please input Scores : ");
		
		int sum  = 0, cnt = 0; 
		int num;
		for(int i = 0;; i++){
			num = input.nextInt();
			if(num < 0) break;
			score[i] = num;
			sum += score[i];
			cnt++;
		}
		double aver = 1.0*sum / cnt;
		int bigger = 0, smaller = 0;
		for(int i = 0; i < cnt; i++){
			if(score[i] >= aver) bigger++;
			else smaller++;
		}
		System.out.println(bigger + " bigger than average !");
		System.out.println(smaller + " smaller than average !");
	}
}

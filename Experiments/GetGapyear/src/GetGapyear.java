/*
 *Name: tangli
 *time: 27/10/09
 *题目： 求21世纪所有闰年
 */

public class GetGapyear {

	public static void main(String[] args) {
		int gapyear, cnt = 0;
		for(int i = 2000; i < 2100; i++) {
			if(i%400 == 0 ||(i%4 == 0 && i%100 != 0 )) {
				gapyear = i;
				cnt++;
				if(cnt%10 !=0)
					System.out.print(gapyear+" ");
				else 
					System.out.println(gapyear);
			}
				
		}

	}

}

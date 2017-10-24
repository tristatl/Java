/*
 * name: tangli
 * time: 2017/10/24
 * problem:
 * (1)随机生成5个互不正交的8位码片M1、M2、M3、M4、M5。
 * (2)分别求出与M1、M2、M3、M4、M5所有正交的8位码片。
 */
public class CDMAcoding {

	static int[][] code = new int[256][100];  //8位码片的编码
	static int[] num = new int[10];  //保存随机生成5位数
	
	public static void main(String[] args) {
		getCode();  // 得到8位码片的所有编码
		createCode(); //随机生成5位两两不正交的码片
		for(int i = 0; i < 5; i++) {
			int ans = 0; //与Mi正交的码片总数
			int res[] = new int[256]; //保存与Mi正交的码片号
			for(int j = 0; j < 256; j++) {
				if(check(j,num[i]) == 0)  //检查是否与Mi正交
					res[ans++] = j; 
			}
			System.out.printf("CDMA码:");
			out(num[i]);
			System.out.printf("total:%d\n\n",ans);
			for(int k = 0; k < 10; k++) 
				out(res[k]);
			System.out.println("\n");
		}
	}
	static void out(int a) {  //以8位编码形式输出
		for(int i = 0; i < 8; i++)
			System.out.print(code[a][i]);
		System.out.print("\n");
	}
	static void getCode() { //得到8位码片的所有编码
		for(int i = 0; i < 256; i++) {
			int tmp = i;
			int cnt = 0;
			while(tmp > 0) {  //十进制转二进制
				code[i][cnt++] = tmp%2;
				tmp /= 2;
			}
			for(int j = 0; j < 8; j++) { //将0变成-1，CDMA编码中只有1和-1
				if(code[i][j] == 0) code[i][j] = -1;
			}
		}
	}		
	static int check(int a, int b) { //检查两码片是否正交
		int sum = 0;
		for(int i = 0; i < 8; i++) {
			sum += code[a][i]*code[b][i];
		}
		return sum; //如果各位相乘和为0，则正交
	}
	static void createCode() { //随机生成5位两两互不正交的码片
		int cnt = 0;
		do {
			Boolean flag = true;
			int random = (int) (256 * Math.random());
			//System.out.println(random);
			for(int i = 0; i < cnt; i++) {
				if(check(i,cnt) == 0) {  
					flag = false;break;
				}
			}
			if(flag) 
				num[cnt++] = random;
			
		}while(cnt < 5);	
	}
}
 

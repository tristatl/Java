/*
 * name : tangli
 * time : 2017/10/26
 *  文件复制，使用FileInputStream读取文件A的字节，使用FileOutputStream写入到文件B
 */

package fileTest;

import java.io.*;

public class FileTest {

	public static void main(String[] args) {
		File f1 = new File("D:\\eclipse-workspace\\fileTest","f1.txt");
		File f2 = new File("D:\\eclipse-workspace\\fileTest","f2.txt");
		copyFile(f1,f2);
	}
	public static void copyFile(File from, File to) {
		
			InputStream input = null;
			OutputStream output = null;
			try {
				input = new FileInputStream(from);  //输入字节流
				output = new FileOutputStream(to);  //输出字节流
				byte[] buff = new byte[1024];
				int bytesRead;
				while((bytesRead = input.read(buff)) > 0) {
					output.write(buff, 0, bytesRead);
				}
				input.close();
				output.close();
			}catch(Exception e) {
				System.out.println("复制文件操作出错");
				e.printStackTrace();
			}
		}
}


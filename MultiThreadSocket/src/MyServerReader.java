
import java.io.DataInputStream;
import java.io.IOException;

public class MyServerReader extends Thread {

	private DataInputStream dis;
	public MyServerReader(DataInputStream dis) {
		this.dis = dis;
	}
	public void run() {
		try {
			while(true) {
				//首先接受客户端发来的信息
				String info = dis.readUTF();
				System.out.println("client says : " + info);
				if(info.equals("bye")) break;
				
			}
			//关闭输入流
			dis.close();
			
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
}

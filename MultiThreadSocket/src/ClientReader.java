
import java.io.DataInputStream;
import java.io.IOException;

public class ClientReader extends Thread{

	private DataInputStream dis;
	public ClientReader(DataInputStream dis) {
		this.dis = dis;
	}
	public void run() {
		try {
			while(true) {
				//接收服务器发来的信息
				String info = dis.readUTF();
				System.out.println("server says : " + info);
				if(info.equals("bye")) break;
			}
			dis.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}

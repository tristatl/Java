

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientWriter extends Thread{

	private DataOutputStream dos;
	//从键盘输入的输入流并封装
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	
	public ClientWriter(DataOutputStream dos) {
		this.dos = dos;
	}
	public void run() {
		try {
			while(true) {
				//向服务器发送连接请求
				String info = br.readLine();
				dos.writeUTF(info);
				if(info.equals("bye")) break;
			}
			dos.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}

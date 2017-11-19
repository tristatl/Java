
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyServerWriter extends Thread{

	private DataOutputStream dos;
	//从键盘输入的输入流，封装该输入流
	private InputStreamReader isr = new InputStreamReader(System.in);
    private	BufferedReader br = new BufferedReader(isr);
	
	public MyServerWriter(DataOutputStream dos) {
		this.dos = dos;
	}
	
	public void run() {
		try {
			while(true) {
				//向客户端发送信息
				String info = br.readLine();
				dos.writeUTF(info);
				dos.flush();
				if(info.equals("bye")) break;
			}
			//关闭输入输出流
			dos.close();
			br.close();
			
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
}

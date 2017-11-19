/*
 * name : TangLi
 * time : 2017/11/19
 * problem: 实现套接字接口，服务器端和客户端通信对话
 * 输入输出流多线程
 * 程序中加入了多线程，不管是服务器端，还是客户端，都可以连续的说话，另一边连续的听
 * 这是服务器端
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	@SuppressWarnings("resource")
	public void service() {
		try {
			//服务器端套接字，等待客户端请求
			ServerSocket ss = new ServerSocket(8888,3);
			Socket s = ss.accept();
			//new Thread(new Handle(s)).start();
			
			//打开输入流，封装输入流
			InputStream is = s.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			//打开输出流，封装输出流
			OutputStream os = s.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			new MyServerReader(dis).start();
			new MyServerWriter(dos).start();
			
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//启动服务器
		ServerTest st = new ServerTest();
		st.service();

	}
}

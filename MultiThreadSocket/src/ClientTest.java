/*
 * name : TangLi
 * time : 2017/11/19
 * problem: 实现套接字接口，服务器端和客户端通信对话
 * 输入输出流多线程
 * 程序中加入了多线程，不管是服务器端，还是客户端，都可以连续的说话，另一边连续的听
 * 这是客户端
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTest {

	public static void main(String[] args)throws Exception {
		//客户端套接字接口指向服务器IP和 port
		String host = "127.0.0.1";
		int port = 8888;
		@SuppressWarnings("resource")
		Socket s = new Socket(host,port);
		//打开输出流，封装输出流
		OutputStream os = s.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		//打开输入流，封装输入流
		InputStream is = s.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		new ClientReader(dis).start();
		new ClientWriter(dos).start();
	}

}

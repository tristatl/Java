/*
 * name : TangLi
 * time : 2017/11/19
 * problem: 实现套接字接口，服务器端和客户端通信对话
 * 这是客户端
 */
import java.io.*;
import java.util.*;
import java.net.*;

public class Client {

	public static void main(String[] args)throws Exception {
		//客户端套接字接口指向服务器IP和 port
		String host = "127.0.0.1";
		int port = 8888;
		Socket s = new Socket(host,port);
		//打开输出流，封装输出流
		OutputStream os = s.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		//打开输入流，封装输入流
		InputStream is = s.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		//从键盘输入的输入流并封装
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		//向服务端发送请求并循环实现交互
		while(true) {
			//首先向服务器发送连接请求
			String info = br.readLine();
			dos.writeUTF(info);
			if(info.equals("bye")) break;
			//然后接收服务器发来的信息
			info = dis.readUTF();
			System.out.println("server says : " + info);
			if(info.equals("bye")) break;
		}
		//关闭输入输出流及套接字接口
		br.close();
		dos.close();
		dis.close();
		s.close();
	}
}

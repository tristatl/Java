/*
 * name : TangLi
 * time : 2017/11/19
 * problem: 实现套接字接口，服务器端和客户端通信对话
 * 这是服务器端
 */
import java.io.*;
import java.util.*;
import java.net.*;

public class Server {
	
	@SuppressWarnings("resource")
	public void service() {
		try {
			//服务器端套接字，等待客户端请求
			ServerSocket ss = new ServerSocket(8888,3);
			Socket s = ss.accept();
			//打开输入流，封装输入流
			InputStream is = s.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			//打开输出流，封装输出流
			OutputStream os = s.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			//从键盘输入的输入流，封装该输入流
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			//接受客户端请求并交互
			while(true) {
				//首先接受客户端发来的信息
				String info = dis.readUTF();
				System.out.println("client says : " + info);
				if(info.equals("bye")) break;
				//向客户端发送信息
				info = br.readLine();
				dos.writeUTF(info);
				dos.flush();
				if(info.equals("bye")) break;
			}
			//关闭输入输出流
			dos.close();
			dis.close();
			br.close();
			
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		//启动服务器
		Server server = new Server();
		server.service();
	}	
}

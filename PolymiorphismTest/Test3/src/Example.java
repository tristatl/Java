
class Singer implements Favor , Person{  // ʵ�ֶ���ӿڣ��ö��Ÿ���
	public void say() { 
		System.out.println("I can say");
	};
	public void eat() {
		System.out.println("I can eat");
	};
	public void drink() {
		System.out.println("I can drink");
	}
}

public class Example {

	public static void main(String[] args) {
		Person p = new Singer();
		Favor f = new Singer();
		p.say();
	//	p.eat();  //ֻ����� �ӿ�Person�б�ʵ�ֵ���
	//	f.say();   //ֻ����� �ӿ�Favor�б�ʵ�ֵ���
		f.eat();
		Singer p1 = (Singer)p; //ǿ��ת��
		p1.eat();
		
	}

}

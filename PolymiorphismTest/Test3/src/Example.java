
class Singer implements Favor , Person{  // 实现多个接口，用逗号隔开
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
	//	p.eat();  //只能输出 接口Person中被实现的类
	//	f.say();   //只能输出 接口Favor中被实现的类
		f.eat();
		Singer p1 = (Singer)p; //强制转换
		p1.eat();
		
	}

}

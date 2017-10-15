public class Son extends Father {   
	protected String name="儿子属性";   
	public String getName() {   
		return name;   
	}   
	public void method() {   
		System.out.println("子类方法，对象类型：" + this.getClass());   
	}   
	public static void main(String[] args) {  
		Father sample = new Son();//向上转型   
		System.out.println("调用的成员："+sample.getName());   
	}   
}

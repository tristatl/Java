public class Son extends Father {   
	protected String name="��������";   
	public String getName() {   
		return name;   
	}   
	public void method() {   
		System.out.println("���෽�����������ͣ�" + this.getClass());   
	}   
	public static void main(String[] args) {  
		Father sample = new Son();//����ת��   
		System.out.println("���õĳ�Ա��"+sample.getName());   
	}   
}

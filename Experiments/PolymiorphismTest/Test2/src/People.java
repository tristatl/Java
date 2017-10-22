
public interface People {
	void say();
}

class Student implements People{
	public void say() {
		System.out.println("I am a student");
	}
}

class Teacher implements People{
	public void say() {
		System.out.println("I am a teacher");
	}
}

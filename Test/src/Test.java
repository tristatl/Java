
public class Test {

	public static void main(String[] args) {
		String s1 = new String("ÖÐ¹ú");
		System.out.println(s1.length());
		//System.out.println(s1.getBytes()[0]);
		byte[] bytes = s1.getBytes();
		//System.out.println(bytes);
		System.out.println(bytes.length);
	}
}

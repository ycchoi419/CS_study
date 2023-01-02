package stack;

public class ReverseString {
	
	public static void main(String[] args) throws Exception {
		String input = "Test Seq 12435";
		String t = reverse(input);
		System.out.println("input string : " + input);
		System.out.println("reversed string : " + t);
	}
	
	private static String reverse(String s) throws Exception {
		ArrayStack<Character> st = new ArrayStack<>(s.length());
		for (int i = 0; i<s.length(); i++) {
			st.push(s.charAt(i));
		}
		String output = "";
		while(!st.isEmpty()) {
			output = output + st.pop();
		}
		return output;
	}
}

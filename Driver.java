import java.io.IOException;

public class Driver {
	public static void main (String args []) throws IOException {
		Parser p = new Parser();
		System.out.println(p.parseStart()); 
	}
}

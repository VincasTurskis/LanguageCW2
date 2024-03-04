import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Lexer {
	private StringTokenizer stream;
  
  public String getNextToken(){
  	try {return stream.nextToken();} catch (NoSuchElementException e) { return ""; }
  }
  
  public Lexer() {
  	try{
			BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));
			stream = new StringTokenizer(stdin.readLine()+" $");
  	} catch (IOException e){}
  }
  
}
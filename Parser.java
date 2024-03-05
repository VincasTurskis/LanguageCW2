public class Parser extends AbstractParser {
// your code goes below
	public float parseStart(){
		/*Grammar in LL(1) form:
		<start> ::= <anyNext>
		<anyNext> ::= <brackets> | <numExpression>
		<brackets> ::= "(" <numExpression> ")"
		<numExpression> ::= "float" <numFollow>
		<numFollow> ::= "*" <anyNext> | "+" <anyNext> | "-" <anyNext>
		*/
		Lexer lexer = new Lexer();
		String curToken = lexer.getNextToken();
		while(!curToken.equals("$"))
		{
			curToken = lexer.getNextToken();
		}
		return 0; /*change this*/
	}
}

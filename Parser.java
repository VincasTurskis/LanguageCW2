public class Parser extends AbstractParser {
// your code goes below
	public float parseStart(){
		/*Grammar in LL(1) form:
		<start> ::= <Bracket> | <Number>
		<Number> ::= "number" <NumberFollow>
		<NumberFollow> ::= <Operator> | epsilon
		<Operator> ::= "+" <Number> | - <Number> | + <Number>
		<OperatorFollow> ::=
		<Bracket> ::= "(" <openBracketFollow>
		<openBracketFollow> ::= <lastOpenBracket> | <lastNumber> ")" 



		<start> ::= <factor><B>
		<B> ::= "*" <factor> <B> | "+" <factor> <B> | "-" <factor> <B> | epsilon
		<factor> ::= "(" <start> ")" | float






		<start> ::= <start> <rem> | <term>
		<rem> ::= "+" <term> | "-" <term>
		<term> ::= <term> "*" <factor> | <factor>
		<factor> ::= "(" <start> ")" | <float>
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

public class Parser extends AbstractParser {
	/*Grammar in LL(1) form:
	<start> ::= <anyNext>
	<anyNext> ::= <brackets> | <numExpression>
	<brackets> ::= "(" <numExpression> ")"
	<numExpression> ::= "float" <numFollow>
	<numFollow> ::= "*" <anyNext> | "+" <anyNext> | "-" <anyNext>
	*/
	public float parseStart(){

		boolean result = EvaluateStart(curTok);
		if(result == true)
		{
			curTok = lex.getNextToken();
			if(!curTok.equals("$"))
			{
				return -1;
			}
			return 1;
		}
		else
		{
			return -1;
		}
	}

	private boolean EvaluateStart(String token)
	{
		return EvaluateAnyNext(token);
	}
	private boolean EvaluateAnyNext(String token)
	{
		return EvaluateBrackets(token) || EvaluateNumExpression(token); 
	}
	private boolean EvaluateBrackets(String token)
	{
		if(!token.equals("("))
		{
			System.out.println("Line 41");
			return false;
		} 
		token = lex.getNextToken();
		if(!EvaluateNumExpression(token))
		{
			return false;
		}
		token = lex.getNextToken();
		if(!token.equals(")"))
		{
			System.out.println("Missing closing bracket");
			return false;
		}
		return true;
	}
	private boolean EvaluateNumExpression(String token)
	{
		try {
			Float.parseFloat(token);
		} catch (Exception e) {
			System.out.println("Failed to parse float");
			return false;
		}
		token = lex.getNextToken();
		return EvaluateNumFollow(token);
	}
	private boolean EvaluateNumFollow(String token)
	{
		if(!token.equals("-") && !token.equals("+") && !token.equals("+"))
		{
			System.out.println("Missing operator");
		}
		token = lex.getNextToken();
		return EvaluateAnyNext(token);
	}
}

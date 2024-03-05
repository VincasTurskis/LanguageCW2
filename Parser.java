public class Parser extends AbstractParser {
	/*Grammar in LL(1) form:
	<start> ::= <anyNext>
	<anyNext> ::= <brackets> | <numExpression>
	<brackets> ::= "(" <anyNext> ")" <numFollow>
	<numExpression> ::= "float" <numFollow>
	<numFollow> ::= "*" <anyNext> | "+" <anyNext> | "-" <anyNext> | epsilon
	*/
	public float parseStart(){

		boolean result = Evaluate();
		if(result == true)
		{
			if(!curTok.equals("$"))
			{
				System.out.println("Found unexpected token");
				return -1;
			}
			return 1;
		}
		else
		{
			return -1;
		}
	}

	private boolean Evaluate()
	{
		return EvaluateAnyNext();
	}
	private boolean EvaluateAnyNext()
	{
		if(curTok.equals("("))
		{
			System.out.println("Found opening bracket");
			return EvaluateBrackets();
		}
		else
		{
			return EvaluateNumExpression();
		}
	}
	private boolean EvaluateBrackets()
	{
		if(!curTok.equals("("))
		{
			System.out.println("Should be opening bracket");
			return false;
		} 
		curTok = lex.getNextToken();
		if(!EvaluateAnyNext())
		{
			return false;
		}
		if(!curTok.equals(")"))
		{
			System.out.println("Missing closing bracket");
			return false;
		}
		System.out.println("Found closing bracket");
		curTok = lex.getNextToken();
		return EvaluateNumFollow();
	}
	private boolean EvaluateNumExpression()
	{
		try {
			Float.parseFloat(curTok);
		} catch (Exception e) {
			System.out.println("Failed to parse float");
			return false;
		}
		System.out.println("Found number");
		curTok = lex.getNextToken();
		return EvaluateNumFollow();
	}
	private boolean EvaluateNumFollow()
	{
		switch (curTok) {
			case "-":
				System.out.println("found \"-\" operator");
				curTok = lex.getNextToken();
				return EvaluateAnyNext();
			case "+":
				System.out.println("found \"+\" operator");
				curTok = lex.getNextToken();
				return EvaluateAnyNext();
			case "*":
				System.out.println("found \"*\" operator");
				curTok = lex.getNextToken();
				return EvaluateAnyNext();
			case ")":
				System.out.println("epsilon");
				return true;
			case "$":
				System.out.println("eof");
				return true;
			default:
				System.out.println("Incorrect num follow token");
				return false;
		}
	}
}

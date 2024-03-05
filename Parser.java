import java.text.ParseException;

public class Parser extends AbstractParser {
	/*Grammar in LL(1) form:
	<start> ::= <anyNext>
	<anyNext> ::= <brackets> | <numExpression>
	<brackets> ::= "(" <anyNext> ")" <numFollow>
	<numExpression> ::= "float" <numFollow>
	<numFollow> ::= "*" <anyNext> | "+" <anyNext> | "-" <anyNext> | epsilon
	*/
	public float parseStart(){
		float result = -1;
		try {
			result = Evaluate();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return -1;
		}
		if(!curTok.equals("$"))
		{
			System.out.println("Error: Found unexpected token \"" + curTok + "\"");
			return -1;
		}
		return result;
	}

	private float Evaluate() throws ParseException
	{
		return EvaluateAnyNext();
	}
	private float EvaluateAnyNext() throws ParseException
	{
		if(curTok.equals("("))
		{
			return EvaluateBrackets();
		}
		else
		{
			return EvaluateNumExpression();
		}
	}
	private float EvaluateBrackets() throws ParseException
	{
		float result = 0;
		if(!curTok.equals("("))
		{
			throw new ParseException("Error: Expected opening bracket, found " + curTok, 0);
		} 
		curTok = lex.getNextToken();
		result = EvaluateAnyNext();
		if(!curTok.equals(")"))
		{
			throw new ParseException("Error: Expected closing bracket, found " + curTok, 0);
		}
		curTok = lex.getNextToken();
		return EvaluateNumFollow(result);
	}
	private float EvaluateNumExpression() throws ParseException
	{
		float result = 0;
		try {
			result = Float.parseFloat(curTok);
		} catch (Exception e) {
			throw new ParseException("Error: Expected floating point expression, but could not parse " + curTok + " as float.", 0);
		}
		curTok = lex.getNextToken();
		return EvaluateNumFollow(result);
	}
	private float EvaluateNumFollow(float originNumber) throws ParseException
	{
		switch (curTok) {
			case "-":
				curTok = lex.getNextToken();
				return (originNumber - EvaluateAnyNext());
			case "+":
				curTok = lex.getNextToken();
				return (originNumber + EvaluateAnyNext());
			case "*":
				curTok = lex.getNextToken();
				return (originNumber * EvaluateAnyNext());
			case ")":
				return originNumber;
			case "$":
				return originNumber;
			default:
				throw new ParseException("Error: A number should be followed by an operator, closing bracket or EOF; Found " + curTok, 0);
		}
	}
}

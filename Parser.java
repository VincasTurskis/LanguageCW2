import java.text.ParseException;

public class Parser extends AbstractParser {
	/*Grammar in LL(1) form:
	<start> ::= <term> { "+" <term> | "-" <term }
	<term> ::= <factor> { "*" <factor> }
	<factor> ::= "(" <start> ")" | float
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
		return EvaluateStart();
	}
	private float EvaluateStart() throws ParseException
	{
		float result = 0;
		result += EvaluateTerm();
		while(curTok.equals("+") || curTok.equals("-"))
		{
			switch (curTok) {
				case "+":
					curTok = lex.getNextToken();
					result += EvaluateTerm();
					break;
				case "-":
					curTok = lex.getNextToken();
					result -= EvaluateTerm();
					break;
				default:
					throw new ParseException("Error: expected an operator", 0);
			}
		}
		return result;
	}
	private float EvaluateTerm() throws ParseException
	{
		float result = 0;
		result += EvaluateFactor();
		while(curTok.equals("*"))
		{
			curTok = lex.getNextToken();
			result *= EvaluateFactor();
		}
		return result;
		
	}
	private float EvaluateFactor() throws ParseException
	{
		float result;
		float parsed = 0;
		boolean foundFloat = false;
		try {
			parsed = Float.parseFloat(curTok);
			foundFloat = true;
		} catch (Exception e) {
			foundFloat = false;
		}
		if(foundFloat)
		{
			curTok = lex.getNextToken();
			return parsed;
		}
		if(curTok.equals("("))
		{
			curTok = lex.getNextToken();
			result = EvaluateStart();
			if(!curTok.equals(")"))
			{
				throw new ParseException("Error: expected closing bracked, found " + curTok, 0);
			}
			curTok = lex.getNextToken();
			return result;
		}
		else
		{
			throw new ParseException("Error: expected opening bracket or number, found " + curTok, 0);
		}

	}
}

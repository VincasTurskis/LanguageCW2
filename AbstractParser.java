public abstract class AbstractParser {
	protected Lexer lex = new Lexer();
	protected String curTok;
	protected AbstractParser(){this.curTok = lex.getNextToken();}	
}
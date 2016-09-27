package Tokenizer;

public class Token {
	public String lexeme;
	public String attribute;
	public int address;
	int tokenNo;
	   
	public Token(String lex, String attr, int addr){
	         lexeme = lex;
	         attribute = attr;
	         address = addr;
	}
}
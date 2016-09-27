package Tokenizer;

public class Attribute {
	
	//private static String temp.lexeme;
	
	public static Token Attributes(Token temp){
	
		if (temp.lexeme.equals( "+")) 
			temp.attribute = "oper";
		else if (temp.lexeme.equals( "-")) 
			temp.attribute = "oper";
		else if (temp.lexeme.equals( "main") ||temp.lexeme.equals ( "end") || temp.lexeme.equals("var") || temp.lexeme.equals("proc") || temp.lexeme.equals("do") ||
				temp.lexeme.equals("if") || temp.lexeme.equals("while") || temp.lexeme.equals( "then") || temp.lexeme.equals( "call")) 
			temp.attribute = "rsWrd";
		else if (temp.lexeme.equals(":=")) 
			temp.attribute = "assnOp";
		else if (temp.lexeme.equals(">=") || temp.lexeme.equals("==")|| temp.lexeme.equals("<")|| temp.lexeme.equals( "<>" )) 
			temp.attribute = "relOp";
		else if (temp.lexeme.equals("(") || temp.lexeme.equals(")") ||temp.lexeme.equals("{") ||temp.lexeme.equals("}") ||temp.lexeme.equals(";") || temp.lexeme.equals("\"")|| temp.lexeme.equals(",")) 
			temp.attribute = "spcChr";
		else if (isInteger(temp.lexeme))
			temp.attribute = "num"; 
	
		return temp;
	}
	
	public static boolean isInteger(String s) {
	      try { 
	         Integer.parseInt(s); 
	      } 
	      catch(NumberFormatException e) { 
	         return false; 
	      }
	    // only got here if we didn't return false
	      return true;
	   }


}




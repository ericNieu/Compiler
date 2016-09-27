package Tokenizer;

public class HasErrors {
	
	public String hasErrors() {
		
		String errorMess = "";
		int para = 0, brac = 0, curl = 0, count = 0;
		// check main method
		if (Tokenizer.getToken(0) != null)
			if (Tokenizer.getToken(0).lexeme.equals("main")) {
				Compiler.setMainCo(Compiler.getMainCo() + 1);
				System.out.println(Compiler.getMainCo());
				if (!Tokenizer.getToken(1).lexeme.equals("(") && !Tokenizer.getToken(2).equals(")")
						&& Tokenizer.getToken(3) != null)// mainCo != 1 is not										// working!!
					if (Compiler.getMainCo() != 1)
						errorMess = errorMess + "the main method is not valid\n";
			}

		for (int i = 0; Tokenizer.getToken(i) != null; i++) {//Check that all paired characters are paired
			if (Tokenizer.getToken(i).lexeme.equals("("))
				para++;
			else if (Tokenizer.getToken(i).lexeme.equals(")"))
				para--;
			else if (Tokenizer.getToken(i).lexeme.equals("["))
				brac++;
			else if (Tokenizer.getToken(i).lexeme.equals("]"))
				brac--;
			else if (Tokenizer.getToken(i).lexeme.equals("{"))
				curl++;
			else if (Tokenizer.getToken(i).lexeme.equals("}"))
				curl--;
			else if (Tokenizer.getToken(i).lexeme.equals(";")) {
				if (curl != 1)
					if (Tokenizer.getToken(i + 1) != null)
						errorMess = errorMess + "there is something after your semicolon at token# " + count
								+ "... ;+. please fix\n";
			}
			if (Tokenizer.getToken(i).lexeme.equals(":=")) {
				if (!Tokenizer.getToken(i - 1).attribute.equals("iden"))
					errorMess = errorMess + "Limproper use of the assnOp at token# " + i + "\n";
				if (Tokenizer.getToken(i + 1).attribute.equals("oper")
						|| Tokenizer.getToken(i + 1).attribute.equals("term")
						|| Tokenizer.getToken(i + 1).attribute.equals("iden"))
					errorMess = "";
				else
					errorMess = errorMess + "Rimproper use of the assnOp at token# " + i + "\n";
			}
			count++;

		}
		if (para != 0) {
			if (para > 0)
				errorMess = errorMess + "There are " + para + " too many left parenthises\n";
			else if (para < 0)
				errorMess = errorMess + "There are " + Math.abs(para) + " too many right parenthises\n";
		}
		if (brac != 0) {
			if (brac > 0)
				errorMess = errorMess + "There are " + brac + " too many left parenthises\n";
			else if (brac < 0)
				errorMess = errorMess + "There are " + Math.abs(brac) + " too many right parenthises\n";
		}
		if (curl != 0) {
			if (curl > 0)
				errorMess = errorMess + "There are " + curl + " too many left parenthises\n";
			else if (curl < 0)
				errorMess = errorMess + "There are " + Math.abs(curl) + " too many right parenthises\n";
		}

		return errorMess;
	}
}

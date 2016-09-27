package Tokenizer;

import java.util.ArrayList;

public class Identifier {

	public String name;
	public int address;
	public static int identNum = 0;
	public static ArrayList<Identifier> ident = new ArrayList<Identifier>();

	public Identifier(String nam, int addr) {
		name = nam;
		address = addr;
		// identNum++;
	}

	public static ArrayList<Identifier> getIdent() {
		return ident;
	}

	public static void setIdent(ArrayList<Identifier> ident) {
		Identifier.ident = ident;
	}

	public static void variables(Token temp, int i) {
		ArrayList<Token> tokens = Tokenizer.getTokens();
		if (temp != null) {
			if (tokens.get(0).lexeme.equals("var")) {
				if (temp.lexeme.equals(","))
					return;
				if (temp.lexeme.equals(";"))
					return;
				if (temp.lexeme.equals("var"))
					return;
				if (!Character.isLowerCase(temp.lexeme.charAt(0)))
					return;
				for (int n = 1; n <= temp.lexeme.length() - 1; n++) {
					if (!Character.isLowerCase(temp.lexeme.charAt(n)) && !Character.isDigit(temp.lexeme.charAt(n)))
						return;
				}
				temp.attribute = "idnt";
				int address = Compiler.getDataAddress();
				// String lex = temp.lexeme;
				ident.add(new Identifier(temp.lexeme, address));
				// System.out.println("oh" + address);
				Compiler.setDataAddress(address + 1);
				// System.out.println(Compiler.getDataAddress());
				// System.out.println("identifier!!!!!!! No. " + identNum);
				temp.address = address;
				identNum++;
				Tokenizer.setToken(temp, i);

			} else if (Tokenizer.getTokens().get(i).attribute == "" || Tokenizer.getTokens().get(i).attribute == "idnt")
				idnt(i);
		}
	}

	public static void idnt(int i) {
		for (int n = 0; n < identNum; n++) {
			if (ident.get(n).name.equals(Tokenizer.getTokens().get(i).lexeme)) {
				Token token = Tokenizer.getTokens().get(i);
				token.attribute = "idnt";
				token.address = ident.get(n).address;
				Tokenizer.setToken(token, i);
				//System.out.println("makes it" + identNum);
				break;
			} else {
				Token token = Tokenizer.getTokens().get(i);
				token.attribute = "error";
				Tokenizer.setToken(token, i);
			}
		}
	}
}

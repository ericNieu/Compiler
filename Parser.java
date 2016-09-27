package Tokenizer;

import java.io.IOException;

public class Parser {

	public int length = 0;
	public String parse, flag;
	private Compiler C;
	public int tokInd;

	public Parser(Compiler C) {
		this.C = C;
		// }
		//
		// public void Parser() {
		length = 0;
		parse = "";
		for (int tokenIndex = 0; tokenIndex < Tokenizer.getTokens().size(); tokenIndex++) {
			length++;
			tokInd = tokenIndex;
			action(tokenIndex); // add to parse the attribute or lexeme of the
								// token
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			// reduce(); // substitute a structure according to grammar rule

		}
		if (length == 1 && parse.equals("stmt "))// && mainCo == 1)// it picking
													// up statement as statement
													// according to earlier
													// comment

		{
			try {
				C.bw.write("Correct Statement");
			} catch (IOException ioe) {
			}
		} else if (length == 0 && parse.equals("")) {
			try {
				C.bw.write("Correct Statement");
			} catch (IOException ioe) {
			}
		} else
			try {
				C.bw.write("Incorrect Statement");
				System.out.println(length);
			} catch (IOException ioe) {
			}

	}

	public void action(int i) {
		flag = "";
		if (Tokenizer.getToken(i).attribute.equals("rsWrd")) {
			parse += Tokenizer.getToken(i).lexeme + " ";
			flag = "L";
		} else if (Tokenizer.getToken(i).attribute.equals("spcChr")) {
			parse += Tokenizer.getToken(i).lexeme + " ";
			flag = "L";
		} else if (Tokenizer.getToken(i).attribute.equals("iden"))
			if (Tokenizer.getToken(i + 1).attribute.equals("relOp")
					|| Tokenizer.getToken(i + 1).attribute.equals("assip")
					|| Tokenizer.getToken(0).lexeme.equals("var")) {
				flag = "A";
				parse += Tokenizer.getToken(i).attribute + " ";
			} else {
				flag = "L";
				parse += "term ";
			}
		else if (Tokenizer.getToken(i).attribute.equals("procName")) {
			flag = "A";
			parse += Tokenizer.getToken(i).attribute + " ";
		} else {
			parse = parse + Tokenizer.getToken(i).attribute + " ";

		}
	}

	public void reduce()// create object array of terms and what they should
	// reduce to and whether they should re-reduce
	{

		// if (parse.indexOf("main ( )") != -1)// all these if statements can be
		// // turned into an object
		// {
		// C.A.start();
		// reducer("main ( )", "spcState");
//		 try {
//		 C.bw.write("Parse = " + parse + '\n');
//		 } catch (IOException ioe) {
//		 }
		// length = length - 2;
		//// reduce();
		// }
		if (parse.indexOf("iden , iden") != -1) {
			reducer("iden , iden", "idntSeq");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 2;
		}
		if (parse.indexOf("procName , procName") != -1) {
			reducer("procName , procName", "procSeq");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 2;
		}
		if (parse.indexOf("proc procSeq ;") != -1) {
			reducer("proc procSeq ;", "spcState");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 2;
		}
//		if (parse.equals("call procName")) {
//			C.A.call();
//			parse = "spclstmt";
//			length = length - 1;
//			// C.AC.call();
//		}

		if (parse.indexOf("proc procName ;") != -1) {
			reducer("proc procName ;", "spcState");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 1;
		}

		if (parse.indexOf("idntSeq , iden") != -1) {
			reducer("idntSeq , iden", "idntSeq");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 2;
			// reduce();
		}
		if (parse.indexOf("var iden ;") != -1) {
			reducer("var iden ;", "spcState");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 2;
		}
		if (parse.indexOf("var idntSeq ;") != -1) {
			reducer("var idntSeq ;", "spcState");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 2;
		}
//		if (parse.indexOf("end") != -1) {
//			C.A.end();
//			reducer("end", "spcState");
//			try {
//				C.bw.write("Parse = " + parse + '\n');
//			} catch (IOException ioe) {
//			}
//			// length = length;
//		}

		if (parse.indexOf("spcState") != -1)// all these if statements can be
		// turned into an object
		{
			// C.A.newStmt();
			reducer("spcState", "stmt");
			try {
				C.bw.write("Parse = " + parse + "\n");
			} catch (IOException ioe) {
			}
			reduce();
		}
//		if (parse.indexOf("term oper term") != -1) {
//			C.A.newExpr();
//			reducer("term oper term", "expr");
//			try {
//				C.bw.write("Parse = " + parse + '\n');
//			} catch (IOException ioe) {
//			}
//			length = length - 2;
//			reduce();
//		}
//		if (parse.indexOf("oper term") != -1) {
//			C.A.newExpr();
//			reducer("oper term", "expr");
//			try {
//				C.bw.write("Parse = " + parse + '\n');
//			} catch (IOException ioe) {
//			}
//			length = length - 1;
//			reduce();
//		}

		// if (parse.indexOf("expr") != -1) {
		//// for(int i=0; i < C.idenNum ; i++)
		//// if(C.indet){}//create a line of code here
		// C.IR.newIden();
		//// C.A.newExpr();
		// C.A.newStmt();
		// reducer("expr", "term");
		// try {
		// C.bw.write("Parse = " + parse + '\n');
		// } catch (IOException ioe) {
		// }
		// reduce();
		// }
		if (parse.indexOf("num") != -1) {
			reducer("num", "term");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			reduce();
		}
		// if (parse.indexOf("( term )") != -1) {
		// reducer("( term )", "term");
		// try {
		// C.bw.write("Parse = " + parse + '\n');
		// } // need to create a new identifier for this value
		// catch (IOException ioe) {
		// }
		// C.IR.newIden();
		// length = length - 2;
		// reduce();
		// }
		// if (parse.indexOf("iden assnOp term ;") != -1) {
		// reducer("iden assnOp term ;", "assnStmt");
		// try {
		// C.bw.write("Parse = " + parse + '\n');
		// } catch (IOException ioe) {
		// }
		// C.IR.assignmentStmt();
		// C.A.assignmentStmt();
		// length = length - 3;
		// reduce();
		// }
		if (parse.indexOf("stmt stmt") != -1) {
			reducer("stmt stmt", "stmt");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 1;
			reduce();
		}
		if (parse.indexOf("{ stmt }") != -1) {
			reducer("{ stmt }", "stmt");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 2;
			reduce();
		}
		if (parse.indexOf("iden relOp term") != -1) {
			reducer("iden relOp term", "relExpr");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 2;
			reduce();
		}
//		if (parse.indexOf("assnStmt") != -1) {
//			C.A.newStmt();
//			reducer("assnStmt", "stmt");
//			try {
//				C.bw.write("Parse = " + parse + '\n');
//			} catch (IOException ioe) {
//			}
//			reduce();
//		}
		// if (parse.indexOf("if relExpr then") != -1) {
		// C.IR.ifStart();
		// C.A.ifStart();
		// reducer("if relExpr then", "ifStart");
		// try {
		// C.bw.write("Parse = " + parse + '\n');
		// } catch (IOException ioe) {
		// }
		// length = length - 2;
		// reduce();
		// }
		// if (parse.indexOf("ifStart stmt") != -1) {
		// C.IR.ifEnd();
		// C.A.ifEnd();
		// reducer("ifStart stmt", "ifState");
		// try {
		// C.bw.write("Parse = " + parse + '\n');
		// } catch (IOException ioe) {
		// }
		// length = length - 1;
		// reduce();
		// }
		if (parse.indexOf("while relExpr do") != -1) {
			reducer("while relExpr do", "whileStart");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 2;
			reduce();
		}
		if (parse.indexOf("whileStart stmt") != -1) {
			reducer("whileStart stmt", "whileState");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			length = length - 1;
			reduce();
		}
		/*
		 * if (parse.indexOf("ifStart stmt") != -1) { reducer("ifStart stmt",
		 * "ifState"); try{C.bw.write("Parse = " + parse + '\n');}
		 * catch(IOException ioe){} length = length -1; reduce(); }
		 */
		if (parse.indexOf("ifState") != -1) {
			// C.A.newStmt();
			reducer("ifState", "stmt");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			reduce();
		}
		if (parse.indexOf("whileState") != -1) {
			// C.A.newStmt();
			reducer("whileState", "stmt");
			try {
				C.bw.write("Parse = " + parse + '\n');
			} catch (IOException ioe) {
			}
			reduce();
		}

	}

	public void reducer(String delim, String reduceTo) {
		if (parse.indexOf(delim) != -1) {
			int buffIndex;
			String buff1, buff2;
			buffIndex = parse.indexOf(delim);

			buff1 = parse.substring(0, buffIndex);
			buff2 = parse.substring(buffIndex + delim.length(), parse.length());
			parse = buff1 + reduceTo + buff2;
			// length= figure this part out, may use stringTokenizer??????
		}
		System.out.println(parse);
		return;
	}
}

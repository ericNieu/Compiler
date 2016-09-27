package Tokenizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Compiler {
	private static String buffer = "if y>=0 then x:=2;//comments";
	private static String location = "/Users/ericoij/Desktop/ProgramIO/";
	private static int DataAddress = 50;
	BufferedWriter bw;
	private static int mainCo = 0;

	public Compiler() {

		try {
			FileReader fr = new FileReader(location + "program.sl");
			FileWriter fw = new FileWriter(location + "program.tok");
			BufferedReader br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			int lineNumber = 1;
			
			while ((buffer = br.readLine()) != null) {
				
				buffer = Tokenizer.tokenizer(buffer);
				Tokenizer.tokenStore(buffer);
				int i = Tokenizer.getTokens().size();
				ArrayList<Token> tokens = new ArrayList<Token>();
				tokens = Tokenizer.getTokens();
				i = 0;
				bw.write("---------------------------------------------------------------\n");
				bw.write("Statement No: " + lineNumber+ "\n");
				if (tokens.size()==0)
					bw.write("Empty Statement\n");
				while (i < tokens.size()) {
						bw.write(String.format("%-15s %-15s %-20s %-10s", "Token No: " + i ,"lexeme: " + tokens.get(i).lexeme , "Attribute: " + tokens.get(i).attribute
								, "Address:" + tokens.get(i).address + "\n"));
					i++;
				}
				HasErrors h = new HasErrors();
				Parser p = new Parser(this);
				//Call Parser 
				lineNumber++;
				buffer = "";
			}
			br.close();
			bw.close();
			fr.close();
			fw.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}


	public static void main(String[] args){
		@SuppressWarnings("unused")
		Compiler app = new Compiler();
	}
	
	public static int getDataAddress() {
		return DataAddress;
	}

	public static void setDataAddress(int dataAddress) {
		DataAddress = dataAddress;
	}


	public static int getMainCo() {
		return mainCo;
	}


	public static void setMainCo(int mainCo) {
		Compiler.mainCo = mainCo;
	}
}

//CS 409 
//Oij
//Eric
//0584206
//9/4/2016
//HW #1
//Tokenizer
package Tokenizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.io.*;

public class Tokenizer
{
	private static StringTokenizer strtok;
	private static String delimLocation = "/Users/ericoij/Desktop/NEIU/CS_409/deliminators.d";
	private static ArrayList <Token> tokens = new ArrayList<Token>();
	
	public static ArrayList<Token> getTokens() {
		return tokens;
	}
	
	public static Token getToken(int i){
		return tokens.get(i);
	}

	public static void setTokens(ArrayList<Token> tokens) {
		Tokenizer.tokens = tokens;
	}
	
	public static void setToken(Token token, int i) {
		Tokenizer.tokens.set(i, token);
	}

	public static String tokenizer(String buffer)
	{
		buffer = commenter(buffer);
		List<String> delims = new ArrayList<String>();
		
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(delimLocation));
			StringTokenizer fltok = new StringTokenizer(br.readLine(), "|");
			
			while(fltok.hasMoreTokens())
			{
				delims.add(fltok.nextToken());
			}
			
			br.close();
		}
		catch(FileNotFoundException fnf){System.out.println("file not found");}
	      catch(IOException ioe){System.out.println("IO exception");};
		
		for(Iterator<String> delim = delims.iterator(); delim.hasNext(); )
		{
			buffer = Split.split(buffer, delim.next() );
		}
		tokens.clear();
		return buffer;
	}
	
	public static void tokenStore (String buffer){
		strtok = new StringTokenizer(buffer, " ");
		int i =0;
		//System.out.println("tokenstore");
		while (strtok.hasMoreTokens()){
			try {
			tokens.add(i, new Token( strtok.nextToken(), "",0)) ; 
			tokens.set(i,Attribute.Attributes(tokens.get(i))) ;
			Identifier.variables(tokens.get(i),i);
			i++;
			} catch(NullPointerException e) {}
		}
	}
	private static String commenter(String stringIn)
	   {
	      String stringOut;
	      if (stringIn.indexOf("//")!=-1)
	         {
	            //int commentIndex = stringIn.indexOf("//");
	            stringOut = stringIn.substring(0 , stringIn.indexOf("//")); 
	         }
	      else
	         stringOut = stringIn;
	      return stringOut;
	   }  
}
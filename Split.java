package Tokenizer;

public class Split {

	public static String split(String buff, String delim) {
		if (buff.indexOf(delim) != -1) {	
			int buffIndex;
			String buff1, buff2;
			buffIndex = buff.indexOf(delim);

			buff1 = buff.substring(0, buffIndex);
			buff2 = buff.substring(buffIndex + delim.length(), buff.length());

			if (buff2.indexOf(delim) != -1) {
				buff2 = split(buff2, delim);
			}
			buff = buff1 + " " + delim + " " + buff2;
		}
		return buff;
	}
}

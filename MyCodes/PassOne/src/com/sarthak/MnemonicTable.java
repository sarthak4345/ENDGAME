//This class stores the Mnemonic table used in Pass One in the form of HashMap


package com.sarthak;
import java.util.*;

public class MnemonicTable {

	HashMap<String,Integer> IS,AD,DL,CC,RG;
	public MnemonicTable() {
			
		//Creating instances of all Statement types
		IS = new HashMap<>();
		AD = new HashMap<>();
		DL = new HashMap<>();
		CC = new HashMap<>();
		RG = new HashMap<>();
		
		//Adding codes of each statements in the hashmaps
		
		DL.put("DS", 01);
		DL.put("DC", 02);
		IS.put("STOP", 0);
		IS.put("ADD",1);
		IS.put("SUB", 2);
		IS.put("MULT", 3);
		IS.put("MOVER", 4);
		IS.put("MOVEM", 5);
		IS.put("COMP", 6);
		IS.put("BC", 7);
		IS.put("DIV", 8);
		IS.put("READ", 9);
		IS.put("PRINT", 10);
		RG.put("AREG", 1);
		RG.put("BREG", 2);
		RG.put("CREG", 3);
		RG.put("DREG", 4);
		AD.put("START", 1);
		AD.put("END", 2);
		AD.put("ORIGIN", 3);
		AD.put("EQU", 4);
		AD.put("LTORG", 5);
		CC.put("LT",1);
		CC.put("LE", 2);
		CC.put("EQ", 3);
		CC.put("GT", 4);
		CC.put("GE", 5);
		CC.put("ANY", 6);
	
	}
	
	//Method for getting the type of statement
	
	public String getType(String s) {
		
		if(AD.containsKey(s))
			return "AD";
		else if(IS.containsKey(s))
				return "IS";
		else if(DL.containsKey(s))
				return "DL";
		else if(CC.containsKey(s))
				return "CC";
		else if(RG.containsKey(s))
				return "RG";
		return "";
						
	}
	
	//Method for getting the machine code of the statement
	
	public int getCode(String s) {
		
		if(AD.containsKey(s))
			return AD.get(s);
		else if(IS.containsKey(s))
				return IS.get(s);
		else if(DL.containsKey(s))
				return DL.get(s);
		else if(CC.containsKey(s))
				return CC.get(s);
		else if(RG.containsKey(s))
				return RG.get(s);
		return -1;
		
	}
	
}

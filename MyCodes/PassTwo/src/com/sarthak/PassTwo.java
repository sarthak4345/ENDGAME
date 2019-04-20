package com.sarthak;

import java.io.*;
import java.util.ArrayList;

public class PassTwo {

		ArrayList<TableRow> SYMTAB,LITTAB;
		
		public PassTwo(){
			
			SYMTAB = new ArrayList<>();
			LITTAB = new ArrayList<>();
		}
		
	
	public static void main(String[] args)throws IOException {
		
		PassTwo two = new PassTwo();
		two.readTables();
		two.generateCode("IC.txt");
		
	
}
	
	//Reading the tables generated in pass one and storing it
	public void readTables() throws FileNotFoundException {
		
		BufferedReader br;
		String line;
		
		try {
			
			br = new BufferedReader(new FileReader("SYMTAB.txt"));
			while((line = br.readLine()) != null) {
				
				String parts[] = line.split("\\s+");
				SYMTAB.add(new TableRow(Integer.parseInt(parts[0]),parts[1],Integer.parseInt(parts[2])));
				
			}
			br.close();
			br = new BufferedReader(new FileReader("LITTAB.txt"));
			while((line=br.readLine()) != null) {
				
				String parts[] = line.split("\\s+");
				LITTAB.add(new TableRow(Integer.parseInt(parts[0]),parts[1],Integer.parseInt(parts[2])));
			}
			br.close();
		}catch(Exception e) {
				
			e.printStackTrace();
		}
		
		
	}
	
	public void generateCode(String filename) throws IOException {
			
			BufferedReader br = new BufferedReader(new FileReader(filename));
			BufferedWriter bw = new BufferedWriter(new FileWriter("MachineCode.txt"));
			String line,code;
			while((line=br.readLine()) != null) {
				
				String parts[] = line.split("\\s+");
				
				//Don't generate machine code for START and END and DL(i.e DS) statements
				if(parts[0].contains("AD,01") || parts[0].contains("DL,01") || parts[0].contains("AD,02")) {
					
					bw.write("\n");
					continue;
				}else if(parts.length == 2) {
					
					//For DC Statement
					if(parts[0].contains("DL")) {
						
						parts[0] = parts[0].replaceAll("[^0-9]","");
						//Double checking that the statement is for DC i.e (DL,02)
						if(Integer.parseInt(parts[0]) == 2) {
							
							//Getting the constant value
							int constant = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
							code = "00\t0\t"+String.format("%03d", constant)+"\n";
							bw.write(code);
						}
					}else if(parts[0].contains("IS")) {
						
						int opcode = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
						
						//Specifically for print IS
						if(opcode == 10 || opcode == 9) {
							
							if(parts[1].contains("S")) {
								
								int symIndex = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
								code = String.format("%02d", opcode)+"\t0\t"+String.format("%03d", SYMTAB.get(symIndex-1).getAddress())+"\n";
								bw.write(code);
							}else if(parts[1].contains("L")) {
								
								int litIndex = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
								code = String.format("%02d", opcode) +"\t0\t"+String.format("%03d", LITTAB.get(litIndex-1).getAddress())+"\n";
								bw.write(code);
							}
						}
						
					}
					
				}
				//For STOP IS
				else if(parts.length == 1 && parts[0].contains("IS")) {
					
					int opcode = Integer.parseInt(parts[0].replaceAll("[^0-9]",""));
					code = String.format("%02d", opcode)+"\t0\t"+String.format("%03d", 0)+"\n";
					bw.write(code);
					//For all other instructions of IS
				}else if(parts.length == 3 && parts[0].contains("IS")) {
					
					int opcode = Integer.parseInt(parts[0].replaceAll("[^0-9]",""));
					int registerCode = Integer.parseInt(parts[1]);
					
					//If Symbol
					if(parts[2].contains("S")) {
						
						int symIndex = Integer.parseInt(parts[2].replaceAll("[^0-9]",""));
						code = String.format("%02d", opcode)+"\t"+registerCode+"\t"+String.format("%03d",SYMTAB.get(symIndex-1).getAddress())+"\n";
						bw.write(code);
					}else if(parts[2].contains("L")) {
						
						int litIndex = Integer.parseInt(parts[2].replaceAll("[^0-9]",""));
						code = String.format("%02d", opcode)+"\t"+registerCode+"\t"+String.format("%03d", LITTAB.get(litIndex-1).getAddress())+"\n";
						bw.write(code);
						
					}
					
				}
			}
			br.close();
			bw.close();
		
	}
	
}

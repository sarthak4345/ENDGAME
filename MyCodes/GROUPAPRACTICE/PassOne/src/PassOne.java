import java.util.*;
import java.io.*;


public class PassOne {

	BufferedReader br;
	LinkedHashMap<String,TableRow>SYMTAB;
	ArrayList<TableRow>LITTAB;
	ArrayList<Integer>POOLTAB;
	int lc=0;
	int symIndex=0;
	int litIndex=0,littab_ptr=0;
	int pooltab_ptr=0;
	MnemonicTable mnemonicTable;
	
	public PassOne() {
		
		SYMTAB = new LinkedHashMap<>();
		LITTAB = new ArrayList<>();
		POOLTAB = new ArrayList<>();
		lc=0;
		POOLTAB.add(0);
		mnemonicTable = new MnemonicTable();
	}
	
	public static void main(String[] args) throws IOException {
		
		PassOne p = new PassOne();
		
		p.parseFile();

	}

	private void parseFile() throws IOException {
		
		br = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("IC.txt"));
		String line,code;
		
		while((line = br.readLine())!=null) {
			
			String parts[] = line.split("\\s+");
			
			if(!parts[0].isEmpty()) {
				
				if(SYMTAB.containsKey(parts[0])) {
					
					SYMTAB.put(parts[0], new TableRow(SYMTAB.get(parts[0]).getIndex(),parts[0],lc));
					
				}else {
					
					SYMTAB.put(parts[0], new TableRow(++symIndex,parts[0],lc));
				}
				
			}
			
			if(parts[1].equals("LTORG")){
				
				int ptr = POOLTAB.get(pooltab_ptr);
				for(int j = ptr;j<littab_ptr;j++) {
					LITTAB.set(j, new TableRow(LITTAB.get(j).getSymbol(),lc));
					code = "(DL,01)\t(C,"+LITTAB.get(j).getSymbol()+")";
					bw.write(code+"\n");
					lc++;
				}
				
				pooltab_ptr++;
				POOLTAB.add(littab_ptr);
				
			}
			
			if(parts[1].equals("START")) {
				
				lc = Integer.parseInt(parts[2]);
				code = "(AD,01)\t(C,"+lc+")\n";
				bw.write(code);
				
			}else if(parts[1].equals("ORIGIN")) {
				
				
				if(parts[2].contains("+")) {
					String splits[] = parts[2].split("\\+");
					lc = SYMTAB.get(splits[0]).getAddress()+Integer.parseInt(splits[1]);
					code = "(AD,03)\t(S,"+SYMTAB.get(splits[0]).getIndex()+")+"+Integer.parseInt(splits[1])+"\n";
					bw.write(code);
					
				}else if(parts[2].contains("-")) {
					
					String splits[] = parts[2].split("\\-");
					lc = SYMTAB.get(splits[0]).getAddress()-Integer.parseInt(splits[1]);
					code = "(AD,03)\t(S,"+SYMTAB.get(splits[0]).getIndex()+")-"+Integer.parseInt(splits[1])+"\n";
					bw.write(code);
					
				}else {
					
					lc = SYMTAB.get(parts[2]).getAddress();
					code = "(AD,03)\t(S,"+SYMTAB.get(parts[2]).getIndex()+")\n";
					bw.write(code);
				}
				
			}
			
			if(parts[1].equals("EQU")) {
				
				int loc = SYMTAB.get(parts[2]).getAddress();
				if(SYMTAB.containsKey(parts[0])) {
					
					SYMTAB.put(parts[0],new TableRow(SYMTAB.get(parts[0]).getIndex(),parts[0],loc));
					
				}else {
					
					SYMTAB.put(parts[0],new TableRow(++symIndex,parts[0],loc));
				}
				bw.write("\n");
				
			}
			
			if(parts[1].equals("DS")) {
				int size = Integer.parseInt(parts[2]);
				lc +=size;
				code = "(DL,02)\t(C,"+size+")\n";
				bw.write(code);
			}else if(parts[1].equals("DC")) {
				lc++;
				int constant = Integer.parseInt(parts[2]);
				code = "(DL,01)\t(C,"+constant+")\n";
				bw.write(code);
			}
			
		if(mnemonicTable.getType(parts[1]).equals("IS")) {
			
			code = "(IS,"+mnemonicTable.getCode(parts[1])+")\t";
			int j = 2;
			while(j<parts.length) {
				
				parts[j] = parts[j].replaceAll(",", "");
				if(mnemonicTable.getType(parts[j]).equals("RG")) {
					
					code += mnemonicTable.getCode(parts[j])+"\t";
				}else if(mnemonicTable.getType(parts[j]).equals("CC")) {
					
					code += "(CC,"+mnemonicTable.getCode(parts[j])+")\t";
				}else {
					
					if(parts[j].contains("=")) {
						
						parts[j] = parts[j].replaceAll("=", "".replaceAll("'", ""));
						LITTAB.add(new TableRow(++litIndex,parts[j],-1));
						littab_ptr++;
						code += "(L,"+litIndex+")";
						
					}else {
						
						if(SYMTAB.containsKey(parts[j])) {
							int index = SYMTAB.get(parts[j]).getIndex();
							code += "(S,"+index+")";
							
						}else {
							
							SYMTAB.put(parts[j], new TableRow(++symIndex,parts[j],-1));
							int ind=SYMTAB.get(parts[j]).getIndex();
							code += "(S,0"+ind+")";
						}
						
					}
				}
				j++;
			}
			
			bw.write(code);
			bw.write("\n");
			lc++;
			
		}
		
		if(parts[1].equals("END")) {
			
			code="(AD,02)";
			bw.write(code+"\n");
			
			int ptr = POOLTAB.get(pooltab_ptr);
			for(int j = ptr;j<littab_ptr;j++) {
				
				LITTAB.set(j, new TableRow(LITTAB.get(j).getSymbol(),lc));
				code = "(DL,01)\t(C,"+LITTAB.get(j).getSymbol()+")";
				bw.write(code+"\n");
				lc++;
			}
			
			pooltab_ptr++;
			POOLTAB.add(littab_ptr);
			
			
		}
			
		
		
			System.out.println("LC:"+lc);
		}
		bw.close();
		printSYMTAB();
		printLITTAB();
		printPOOLTAB();
		
	}
	
	void printLITTAB() throws IOException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("LITTAB.txt"));
		System.out.println("\nLiteral Table\n");
		//Processing LITTAB
		for(int i=0;i<LITTAB.size();i++)
		{
			TableRow row=LITTAB.get(i);
			System.out.println(i+"\t"+row.getSymbol()+"\t"+row.getAddress());
			bw.write((i+1)+"\t"+row.getSymbol()+"\t"+row.getAddress()+"\n");
		}
		bw.close();
	}
	void printPOOLTAB() throws IOException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("POOLTAB.txt"));
		System.out.println("\nPOOLTAB");
		System.out.println("Index\t#first");
		for (int i = 0; i < POOLTAB.size(); i++) {
			System.out.println(i+"\t"+POOLTAB.get(i));
			bw.write((i+1)+"\t"+POOLTAB.get(i)+"\n");
		}
		bw.close();
	}
	void printSYMTAB() throws IOException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("SYMTAB.txt"));
		//Printing Symbol Table
		java.util.Iterator<String> iterator = SYMTAB.keySet().iterator();
		System.out.println("SYMBOL TABLE");
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			TableRow value = SYMTAB.get(key);

			System.out.println(value.getIndex()+"\t" + value.getSymbol()+"\t"+value.getAddress());
			bw.write(value.getIndex()+"\t" + value.getSymbol()+"\t"+value.getAddress()+"\n");
		}
		bw.close();
	}

}

import java.io.*;
import java.util.*;

public class MacroOne {
	
	public static void main(String[] args) throws IOException {
		
		FileWriter mntWriter = new FileWriter("MNT.txt");
		FileWriter pntabWriter = new FileWriter("PNTAB.txt");
		FileWriter kpdtWriter = new FileWriter("KPDT.txt");
		FileWriter mdtWriter = new FileWriter("MDT.txt");
		FileWriter ic = new FileWriter("IC.txt");
		LinkedHashMap<String,Integer> pntab = new LinkedHashMap<>();
	
		BufferedReader br = new BufferedReader(new FileReader("input.asm"));
		
		int pp=0,kp=0,mdtp=1,kpdtp=0,flag=0;
		int paramNo=1;
		
		String line,macroname = null;
		
		while((line = br.readLine())!=null) {
			String parts[] = line.split("\\s+");
			
			if(parts[0].equals("MACRO")) {
				
				flag = 1;
				line = br.readLine();
				parts = line.split("\\s+");
				macroname = parts[0];
				if(parts.length <=1) {
					mntWriter.write(parts[0]+":\t"+pp+"\t"+kp+"\t"+mdtp+"\t"+((kp==0)?(kpdtp):(kpdtp+1)));
					continue;
				}
				
				for(int i=1;i<parts.length;i++) {
					parts[i] = parts[i].replaceAll("[&,]", "");
					System.out.println(parts[i]);
					if(parts[i].contains("=")) {
						
						System.out.println("IN KP");
						String splits[] = parts[i].split("=");
						pntab.put(splits[0], paramNo++);
						if(splits.length == 2) {
							
							kpdtWriter.write(splits[0]+"\t"+splits[1]+"\n");
							kp++;
							
						}else {
							
							kpdtWriter.write(splits[0]+"\t--\n");
							kp++;
						}
						
					
					}else {
						
						System.out.println("IN PP:"+parts[i]);
						String a = parts[i];
						pntab.put(a, paramNo++);
						pp++;
						
					}
				}
				
				mntWriter.write(macroname+":\t"+pp+"\t"+kp+"\t"+mdtp+"\t"+((kp==0)?kpdtp:kpdtp+1)+"\n");
			}else if(parts[0].equals("MEND")) {
				
				mdtWriter.write(line+"\n");
				kpdtp += kp;
				flag=kp=pp=0;
				paramNo=1;
				mdtp++;
				pntabWriter.write(macroname+":\t");
				Iterator<String> itr = pntab.keySet().iterator();
				while(itr.hasNext()) {
					String temp=itr.next();
					pntabWriter.write(temp+"\t");
					
				}
				pntabWriter.write("\n");
				pntab.clear();
				
			}else if(flag == 1) {
				
				for(int i=0;i<parts.length;i++) {
					
					
					if(parts[i].contains("&")) {
						
						parts[i] = parts[i].replaceAll("[&,]","");
						int number = pntab.get(parts[i]);
						mdtWriter.write("(P,"+number+")\t");
						
					}else {
						
						mdtWriter.write(parts[i]+"\t");
					}
				}
				
				mdtWriter.write("\n");
				mdtp++;
			}else {
				
				ic.write(line+"\n");
			}
			
			
			
		}
		br.close();
		mntWriter.close();
		mdtWriter.close();
		pntabWriter.close();
		ic.close();
		kpdtWriter.close();
		
	}

}

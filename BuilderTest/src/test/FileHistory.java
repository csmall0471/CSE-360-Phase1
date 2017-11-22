package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileHistory {
	private LinkedList<FileParser> historyFiles;
	public FileHistory(){
		historyFiles = new LinkedList<FileParser>();
	}
	public void add(FileParser toAdd){
		historyFiles.add(toAdd);
	}
	
	public FileParser get(int toGet){
		return historyFiles.get(toGet);
	}
	
	public void combine(FileParser first, FileParser second){
		//convert FileParser objects to Files
		if(first != null && second != null){
			File firstFile = first.getFile();
			File secondFile = second.getFile();
			
			
			//Combine Files
			try{
				//Write to first File
				FileWriter fw = new FileWriter(firstFile, true);
				BufferedWriter fileScan = new BufferedWriter(fw);
				
				//Read from Second File
				FileReader fr = new FileReader(secondFile);
				BufferedReader fileScan2 = new BufferedReader(fr);
				
				String currentLine;
				
				//Append all lines from second file to first file
				fileScan.append("\n");
				while((currentLine = fileScan2.readLine()) != null){
					fileScan.append(currentLine + "\n");
				}
				fileScan.close();
				fileScan2.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			
			//Find and delete second file from linkedlist
			for(int i = 0; i < historyFiles.size(); i++){
				if(historyFiles.get(i).equals(second)){
					historyFiles.remove(i);
				}
			}
		}
	}
	
	public int size(){
		return historyFiles.size();
	}
}

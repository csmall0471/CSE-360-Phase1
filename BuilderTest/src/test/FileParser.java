package test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class FileParser {
	// instance variables
	
	private File inFile; // file to parse through
	
	// variables holding the file information
	private int numLines;
	private int blankLines;
	private int numSpace;
	private int numWords;
	private double aveWordLen;
	private double aveCharsPerLine;
	private String mostCommonWord;
	
	public FileParser(File inFile)	// constructor which initializes instance file to parameter file
	{
		this.inFile = inFile;
		// initialize all instance variables to sentinal values
		this.numLines = 0;
		this.blankLines = 0;
		this.numSpace = 0;
		this.numWords = 0;
		this.aveWordLen = 0;
		this.aveCharsPerLine = 0;
		this.mostCommonWord = " ";
	}
	
	// computes all of the relevant information for the file
	public void compute()
	{
		try{
			FileReader fr = new FileReader(inFile);
			BufferedReader fileScan = new BufferedReader(fr);
			
			String currentLine = "sentinal"; // an arbitrary line in the file
			int currentCharsInLine = 0;
			double aveWordLen = 0; // average word length
			
			while(currentLine != null)
			{
				// evaluates information on a line by line basis
				currentLine = fileScan.readLine();
				numLines++; // increment number of lines in the file
				if(currentLine == "\n") // if oneLine is blank
					blankLines++;
				
				// evaluates information on a char by char basis
				currentCharsInLine = 0;	// reset this counter for each new line
				int i = 0;
				char currentChar = currentLine.charAt(i);
				
				while(currentChar != '\n')
				{
					if(currentChar == ' ')
					{
						numSpace++;
						numWords++;
					}
					else
						currentCharsInLine++;
					i++;
				}
				
				aveCharsPerLine += currentCharsInLine; // add the num of chars in the line we just parsed
			} 
			if(numLines != 0)
				aveCharsPerLine /= numLines; // up to this point, aveCharsPerLine is a running sum
			
			fileScan.close();
		}
		catch(IOException e)
		{
			System.err.println("File not found");
		}
	}
}









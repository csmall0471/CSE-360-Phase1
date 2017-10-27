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
	private double avgWordLen;
	private double avgCharsPerLine;
	private String mostCommonWord;
	
	public FileParser(File inFile)	// constructor which initializes instance file to parameter file
	{
		this.inFile = inFile;
		// initialize all instance variables to sentinal values
		this.numLines = 0;
		this.blankLines = 0;
		this.numSpace = 0;
		this.numWords = 0;
		this.avgWordLen = 0;
		this.avgCharsPerLine = 0;
		this.mostCommonWord = " ";
	}
	
	// getters
	public int getNumLines()
	{
		return numLines;
	}
	public int getBlankLines()
	{
		return blankLines;
	}
	public int getNumSpaces()
	{
		return numSpace;
	}
	public int getNumWords()
	{
		return numWords;
	}
	public double getAvgWordLen()
	{
		return avgWordLen;
	}
	public double getCharsPerLine()
	{
		return avgCharsPerLine;
	}
	public String getMostCommonWord()
	{
		return mostCommonWord;
	}	
	
	// computes all of the relevant information for the file
	public void compute()
	{
		try{
			FileReader fr = new FileReader(inFile);
			BufferedReader fileScan = new BufferedReader(fr);
			
			String currentLine; // an arbitrary line in the file
			int currentCharsInLine = 0;
			double aveWordLen = 0; // average word length
			
			while((currentLine = fileScan.readLine()) != null)
			{
				// evaluates information on a line by line basis
				numLines++; // increment number of lines in the file

				if(currentLine.trim().equals(""))
					blankLines++;
				else
					numWords++;
				
				// evaluates information on a char by char basis
				currentCharsInLine = 0;	// reset this counter for each new line
				int i = 0;
				char currentChar;
				
				while(i < currentLine.length())
				{
					currentChar = currentLine.charAt(i);
					if(currentChar == ' ')
					{
						numSpace++;
						numWords++;
					}
					else
						currentCharsInLine++;
					i++;
				} // end while
				
				avgCharsPerLine += currentCharsInLine; // add the num of chars in the line we just parsed
			} // end while
			
			System.out.println("char sum: " + avgCharsPerLine);
			
			if(numLines != 0)
				avgCharsPerLine /= numLines; // up to this point, aveCharsPerLine is a running sum
			
			fileScan.close();
		}
		catch(IOException e)
		{
			System.err.println("File not found");
		}
	}
}









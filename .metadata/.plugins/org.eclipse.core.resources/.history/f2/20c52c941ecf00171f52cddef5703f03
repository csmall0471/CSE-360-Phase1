package test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class FileParser {
	// instance variables
	
	private File inFile; // file to parse through
	
	// variables holding the file information
	private int numLines;
	private int blankLines;
	private int numSpace;
	private int numWords;
	private double avgCharsPerLine;
	private double avgWordLen;
	private String mostCommonWord;
	private Map<String, Integer> words;
	
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
		words = new HashMap<String, Integer>();
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
	public File getFile(){
		return inFile;
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
				String newWord = "";
				
				while(i < currentLine.length())
				{
					currentChar = currentLine.charAt(i);
					if(currentChar == ' ' || (i == currentLine.length() - 1))
					{
						if(currentChar == ' ')
						{
							numSpace++;
							try{
								if(currentLine.charAt(i+1) != ' ')
									numWords++;
							}catch(StringIndexOutOfBoundsException e){
								// do nothing
							}
						}
						
						newWord += currentChar; // add the final char to the word, and place it in hashmap
						newWord = newWord.toLowerCase().trim();
						
						if(words.containsKey(newWord))
							words.put(newWord, words.get(newWord) + 1);	// place the word in the map and increase count
						else
							words.put(newWord, 1);		// place the word in the map for the first time
						
						//System.out.println("value: " + newWord + " key: " + words.get(newWord));

						newWord = ""; 		// reset the new word string
					}
					else
					{
						currentCharsInLine++;
						newWord += currentChar; 		// append character to word string
					}
					i++;
				} // end while
				
				avgCharsPerLine += currentCharsInLine; // add the num of chars in the line we just parsed
			} // end while
			
			if(numLines != 0)
				avgCharsPerLine /= numLines; // up to this point, aveCharsPerLine is a running sum
			
			fileScan.close();
			calcWordData();
			removePunctuation();
		}
		catch(IOException e)
		{
			System.err.println("File not found");
		}
	}

	// calculates average length and most common word
	private void calcWordData()
	{
		if(!words.isEmpty())
		{
			int maxInstances = Collections.max(words.values()); // the number of times the max num was seen
	
			for(Map.Entry<String, Integer> entry : words.entrySet())
			{
				if(entry.getValue() == maxInstances)
					mostCommonWord = entry.getKey();
				avgWordLen += entry.getKey().length();
			}
			avgWordLen /= words.size();
		}
	}
	
	// returns the contents of the file, but without any punctuation
	public File removePunctuation()
	{
		File noPunctuation = inFile;
		try{
			FileReader fr = new FileReader(noPunctuation);
			FileWriter fw = new FileWriter("no_punc.txt");
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			String currentLine;
			bw.write("");
			while((currentLine = br.readLine()) != null)
			{	
				StringBuilder sb = new StringBuilder(currentLine);
				for(int i = sb.length() - 1; i >= 0; i--)
				{
					switch(sb.charAt(i))
					{
					case '.':
					case ',':
					case '?':
					case '!':
					case '-':
					case ':':
					case ';':
						sb.deleteCharAt(i);
					default:
						break;
					}
				}
				bw.append(sb.toString());
			}
			br.close();
			bw.close();
			
		} catch(FileNotFoundException e){
			// nothing
		} catch(IOException e){
			// nothing
		}
		return noPunctuation;
	}
	
	public void printHashMap()
	{
		for(Map.Entry<String, Integer> entry : words.entrySet())
			System.out.println("Word: " + entry.getKey() + " was seen " + entry.getValue() + " times");
	}
	
	public String toString()
	{
		return inFile.toString();
	}
	
	
}
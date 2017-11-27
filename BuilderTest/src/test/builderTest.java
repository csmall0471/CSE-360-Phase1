package test;
import java.io.*;
import java.util.*;
import java.awt.EventQueue;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import java.awt.image.*;
import java.lang.*;
import javax.imageio.*;

public class builderTest extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtInputFileName;
	private JButton btnCheckValidity;
	private JScrollPane scrollPane;
	private JTextArea textPane;
	private JButton btnNewButton;
	private JButton btnBlankLines;
	private JButton btnCountWords;
	private JButton btnCountSpaces;
	private JButton btnAvgCharline;
	private JButton btnAvgWordLength;
	private JButton btnMostComWord;
	int currFile;
	LinkedList<String[]> history;
	FileHistory historyFiles;
	JComboBox<FileParser> fileHistory;
	JComboBox<FileParser> combineFirst;
	JComboBox<FileParser> combineSecond;
	private JButton combineBtn;
	private JButton removePunc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					builderTest frame = new builderTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public builderTest() {
		super();
		this.history = new LinkedList<String[]>();
		this.historyFiles = new FileHistory();
		this.currFile = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 550);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.contentPane.setLayout(null);
		this.contentPane.setBackground(Color.white);
		
		//Input File
		this.txtInputFileName = new JTextField();
		this.txtInputFileName.setForeground(Color.GRAY);
		this.txtInputFileName.setText("Input New File");
		this.txtInputFileName.setBounds(6, 15, 130, 29);
		this.add(txtInputFileName);
		this.txtInputFileName.setColumns(10);
		
		//Main Text Area
		this.textPane = new JTextArea();
		this.scrollPane = new JScrollPane(textPane);
		this.scrollPane.setBounds(149, 75, 455, 340);
		this.scrollPane.setBackground(new Color(220,220,220));
		this.scrollPane.setForeground(new Color(94,173,255));
		this.add(scrollPane);
		
		//File History
		this.fileHistory = new JComboBox<FileParser>();
		this.fileHistory.setBounds(245, 24, 120, 29);
		this.fileHistory.addItem(null);
		this.add(fileHistory);
		
		//Combine Files
		JLabel combine = new JLabel("Combine");
		combine.setFont(new Font("garamond", Font.BOLD | Font.ITALIC, 20));
		combine.setForeground(new Color(94,173,255));
		combine.setBounds(19, 380, 150, 20);
		contentPane.add(combine);
		
		JLabel firstFile = new JLabel("First");
		firstFile.setFont(new Font("garamond",Font.PLAIN, 15));
		firstFile.setForeground(new Color(94,173,255));
		firstFile.setBounds(3, 400, 75, 15);
		contentPane.add(firstFile);
		
		JLabel secondFile = new JLabel("Second");
		secondFile.setFont(new Font("garamond", Font.PLAIN, 15));
		secondFile.setForeground(new Color(94,173,255));
		secondFile.setBounds(3, 450, 75, 15);
		contentPane.add(secondFile);
		
		this.combineFirst = new JComboBox<FileParser>();
		this.combineFirst.setBounds(3, 418, 120, 29);
		this.combineFirst.addItem(null);
		this.add(combineFirst);
		
		this.combineSecond = new JComboBox<FileParser>();
		this.combineSecond.setBounds(3, 465, 120, 29);
		this.combineSecond.addItem(null);
		this.add(combineSecond);
		
		//Labels
		JLabel textPaneTitle = new JLabel("Console");
		textPaneTitle.setFont(new Font("garamond", Font.BOLD | Font.ITALIC, 20));
		textPaneTitle.setForeground(new Color(94,173,255));
		textPaneTitle.setBounds(335, 50, 150, 20);
		//contentPane.add(textPaneTitle);
		
		JLabel functions = new JLabel("Functions");
		functions.setFont(new Font("garamond", Font.BOLD | Font.ITALIC, 20));
		functions.setForeground(new Color(94,173,255));
		functions.setBounds(19, 50, 150, 20);
		contentPane.add(functions);

		
	try {		
		this.combineBtn = new JButton("Combine");
		this.combineBtn.setForeground(new Color(94,173,255));
		this.combineBtn.addActionListener(this);
		this.combineBtn.setBounds(3, 490, 115, 29);
		this.add(combineBtn);
		
		this.btnCheckValidity = new JButton("Insert");
		this.btnCheckValidity.setContentAreaFilled(false);
		this.btnCheckValidity.setBorder(BorderFactory.createEmptyBorder());
		this.btnCheckValidity.setForeground(new Color(94,173,255));		
		this.btnCheckValidity.addActionListener(this);
		this.btnCheckValidity.setBounds(105, 16, 115, 29);
		this.add(btnCheckValidity);
		
		this.btnNewButton = new JButton("Count Lines");
		this.btnNewButton.setContentAreaFilled(false);
		this.btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		this.btnNewButton.setBounds(3, 75, 131, 29);
		this.add(btnNewButton);
		this.btnNewButton.addActionListener(this);
						
		this.btnBlankLines = new JButton("Blank Lines");
		this.btnBlankLines.setContentAreaFilled(false);
		this.btnBlankLines.setBorder(BorderFactory.createEmptyBorder());
		this.btnBlankLines.setBounds(3, 115, 131, 29);
		this.add(btnBlankLines);
		this.btnBlankLines.addActionListener(this);
			
		this.btnCountWords = new JButton("Count Words");
		this.btnCountWords.setContentAreaFilled(false);
		this.btnCountWords.setBorder(BorderFactory.createEmptyBorder());
		this.btnCountWords.setBounds(3, 155, 131, 29);
		this.add(btnCountWords);
		this.btnCountWords.addActionListener(this);
			
		this.btnCountSpaces = new JButton("Count Spaces");
		this.btnCountSpaces.setContentAreaFilled(false);
		this.btnCountSpaces.setBorder(BorderFactory.createEmptyBorder());
		this.btnCountSpaces.setBounds(3, 195, 131, 29);
		this.add(btnCountSpaces);
		this.btnCountSpaces.addActionListener(this);
			
		this.btnAvgCharline = new JButton("Avg Char/Line");
		this.btnAvgCharline.setContentAreaFilled(false);
		this.btnAvgCharline.setBorder(BorderFactory.createEmptyBorder());
		this.btnAvgCharline.setBounds(3, 235, 131, 29);
		this.add(btnAvgCharline);
		this.btnAvgCharline.addActionListener(this);
				
		this.btnAvgWordLength = new JButton("Avg Word Length");
		this.btnAvgWordLength.setContentAreaFilled(false);
		this.btnAvgWordLength.setBorder(BorderFactory.createEmptyBorder());
		this.btnAvgWordLength.setBounds(3, 275, 131, 29);
		this.add(btnAvgWordLength);
		this.btnAvgWordLength.addActionListener(this);
				
		this.btnMostComWord = new JButton("Most Common Word");
		this.btnMostComWord.setContentAreaFilled(false);
		this.btnMostComWord.setBorder(BorderFactory.createEmptyBorder());
		this.btnMostComWord.setBounds(5, 315, 131, 29);
		this.add(btnMostComWord);
		this.btnMostComWord.addActionListener(this);
		
		this.removePunc = new JButton("Remove Punctuation");
		this.removePunc.setContentAreaFilled(false);
		this.removePunc.setBorder(BorderFactory.createEmptyBorder());
		this.removePunc.setBounds(5, 355, 131, 29);
		this.add(removePunc);
		this.removePunc.addActionListener(this);
		
		JLabel lblHelp = new JLabel("Help");
		lblHelp.setFont(new Font("garamond", Font.BOLD, 14));
		lblHelp.setForeground(new Color(94,173,255));
		lblHelp.setBounds(413, 7, 40, 15);
		contentPane.add(lblHelp);
		
		String[] helpItems = {"...", "File Input", "Calculations", "Multiple File Input"};
		JComboBox<String> cbxHelp = new JComboBox<String>(helpItems);
		cbxHelp.setBounds(374, 24, 114, 29);
		cbxHelp.setEditable(false);
		cbxHelp.setSelectedIndex(0);
		cbxHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selection = (String)((JComboBox<String>)e.getSource()).getSelectedItem();
				switch(selection) {
					case "...":
						textPane.setText("");
						break;
					case "File Input":
						textPane.setText("FILE INPUT\n\n"
								+ "In the input textbox, please enter the name of the file you wish to process.\n"
								+ "Then, click the \"Input File\" button to confirm that the entered file name is valid.\n\n"
								+ "If the file name is not valid, please make sure that you correctly spelled the name of the file "
								+ "that you wish to process and that the file indeed exists.");
						break;
					case "Calculations":
						textPane.setText("CALCULATIONS\n\n"
								+ "Click one of the buttons on the left to perform calculations on the file.\n\n"
								+ "You can choose perform the following actions:\n"
								+ "- Count Lines:\tCount the total number of lines in the file.\n"
								+ "- Blank Lines:\tCount the number of blank lines in the file.\n"
								+ "- Count Words:\tCount the total number of words in the file.\n"
								+ "- Count Spaces:\tCount the total number of spaces in the file.\n"
								+ "- AVG Char\\Line:\tCalculate the average number of characters per line.\n"
								+ "- AVG Word Len:\tCalculate the average length of the words in the file.\n"
								+ "- Most Com Word:\tDetermine which word appears the most in the file.");
						break;
					case "Multiple File Input":
						textPane.setText("MULTIPLE FILE INPUT\n\n"
								+ "Click on the \"File History\" button to display a list of files that have been processed.\n"
								+ "You can select the files from this list and compare their statistics (line count, word count, etc.) "
								+ "with each other.");
						break;
				}
				
				
			}
		});
		this.add(cbxHelp);
		
		JLabel btnHistory = new JLabel("File History");
		btnHistory.setFont(new Font("garamond", Font.BOLD, 14));
		btnHistory.setForeground(new Color(94,173,255));
		btnHistory.setBounds(265, 1, 120, 29);
		this.add(btnHistory);
		fileHistory.addActionListener(new ActionListener(){
		 			public void actionPerformed(ActionEvent e){
		 				//find file name in ArrayList
		 				JComboBox box = (JComboBox) e.getSource();
		 				
		 				for(int i = 0; i<historyFiles.size();i++){
		 					textPane.setText("");
		 					if(box.getSelectedItem() != null && box.getSelectedItem().equals(historyFiles.get(i))){
		 						currFile = i; //Save index of file being used
		 						textPane.setText("Preview:\n\n");
		 						//Prints out the contents of file
		 						try {
									FileReader fr = new FileReader(historyFiles.get(i).getFile());
									BufferedReader br = new BufferedReader(fr);
									String line;
			 						while((line = br.readLine()) != null){
			 							textPane.setText(textPane.getText() + line + "\n");
			 						}
			 						br.close();
		 						} catch (IOException e1) {
								}
		 						break;
		 					}
		 				}
		 			}
		 		}); 
		} catch (Exception e) {
			
		}
	}
	
	public boolean checkValidity(String fileName) throws IOException
	{
		BufferedReader br = new BufferedReader(
		new InputStreamReader(new FileInputStream(fileName)));
		if(br.ready() == true)
		{
			br.close();
			return true;
		}else
		{
			br.close();
			return false;
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCheckValidity) {
			textPane.setText("");
			String fileName = txtInputFileName.getText(); //inputed by user 
			try {
				BufferedReader br;
				br = new BufferedReader(
				new InputStreamReader(new FileInputStream(fileName)));
				textPane.setText(textPane.getText() + "File inputed\n\nPlease make a Selection\n");
				
				int count = 0;
				while(br.readLine() !=null)//counts the number of lines in the file
				{
					count++;
				}
				br.close();
				String ary[] = new String[count]; //string array will hold the lines of the txt file
				br = new BufferedReader(
				new InputStreamReader(new FileInputStream(fileName)));
				textPane.setText(textPane.getText() + "\n_______________File Text_______________________\n");				
				for(int i = 0; i< count; i++)//insert the strings into the array
				{
					String toBeInserted = br.readLine();
					ary[i] = toBeInserted;
					textPane.setText(textPane.getText() + toBeInserted +"\n");
				}
				textPane.setText(textPane.getText() + "\n_______________File Information________________\n");
				history.add(ary);

				File inFile = new File(fileName); // input file to be parsed
				
				FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
				fileData.compute(); // uses the file to compute the data
				historyFiles.add(fileData);
				fileHistory.addItem(fileData);//remove all previous directories, show only file name
				combineFirst.addItem(fileData);
				combineSecond.addItem(fileData);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				textPane.setText(textPane.getText() + "File name not found\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else{
			FileParser file = historyFiles.get(currFile);
			NumberFormat doubleFormat = new DecimalFormat("#0.00");
			if (e.getSource() == btnNewButton) {
				textPane.setText(textPane.getText()+"Line Count: "+ file.getNumLines()+"\n");
			} else if(e.getSource() == btnBlankLines){
				textPane.setText(textPane.getText()+"Blank Line Count: "+ file.getBlankLines()+"\n");
			} else if(e.getSource() == btnCountWords){
				textPane.setText(textPane.getText()+"Word Count: "+ file.getNumWords()+"\n");
			} else if(e.getSource() == btnCountSpaces){
				textPane.setText(textPane.getText()+"Space Count: "+ file.getNumSpaces()+"\n");
			} else if(e.getSource() == btnAvgCharline){
				textPane.setText(textPane.getText()+"Average Char/Line: "+ doubleFormat.format(file.getCharsPerLine())+"\n");		
			} else if(e.getSource() == btnAvgWordLength){
				textPane.setText(textPane.getText()+"Average Word Length: "+ doubleFormat.format(file.getAvgWordLen())+"\n");
			} else if(e.getSource() == btnMostComWord){
				textPane.setText(textPane.getText()+"Most Common Word: "+ file.getMostCommonWord()+"\n");
			} else if(e.getSource() == combineBtn){
				FileParser first = null;
				FileParser second = null;
				for(int i=0; i < historyFiles.size();i++){
					if(combineFirst.getSelectedItem().equals(historyFiles.get(i))){
						first = historyFiles.get(i);
					}
					if(combineSecond.getSelectedItem().equals(historyFiles.get(i))){
						second = historyFiles.get(i);
						fileHistory.removeItem(second);
					}
				}
				historyFiles.combine(first, second);
				
			}else if (e.getSource()==removePunc) {
				file.removePunctuation();
			}
		}
	}
}
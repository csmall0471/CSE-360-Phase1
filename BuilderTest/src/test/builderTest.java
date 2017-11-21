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
import java.nio.file.Files;
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
<<<<<<< HEAD
	int currFile;
=======
	private JButton btnCheckValidity;
	private JTextPane textPane;
	private JButton btnNewButton;
	private JButton btnBlankLines;
	private JButton btnCountWords;
	private JButton btnCountSpaces;
	private JButton btnAvgCharline;
	private JButton btnAvgWordLength;
	private JButton btnMostComWord;
	int currFile;
	LinkedList<String[]> history;
	LinkedList<FileParser> historyFiles;
	JComboBox fileHistory;
	JList<String> combineFiles;
	private JButton combineBtn;
>>>>>>> refs/remotes/origin/master

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
<<<<<<< HEAD
		LinkedList<String[]> history = new LinkedList<String[]>();
		LinkedList<String> historyFiles = new LinkedList<String>();
		currFile = 0;
=======
		super();
		this.history = new LinkedList<String[]>();
		this.historyFiles = new LinkedList<FileParser>();
		this.currFile = 0;
>>>>>>> refs/remotes/origin/master
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 453);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.contentPane.setLayout(null);
		this.contentPane.setBackground(Color.white);
		
<<<<<<< HEAD
		JComboBox<String> fileHistory = new JComboBox<String>();
		fileHistory.setBounds(332, 24, 120, 29);
		contentPane.add(fileHistory);
		
		JLabel lblNewLabel = new JLabel("Text Analyzer");
		lblNewLabel.setBounds(179, 6, 91, 16);
		contentPane.add(lblNewLabel);
=======
		this.txtInputFileName = new JTextField();
		this.txtInputFileName.setForeground(Color.GRAY);
		this.txtInputFileName.setText("Input New File");
		this.txtInputFileName.setBounds(6, 15, 130, 29);
		this.add(txtInputFileName);
		this.txtInputFileName.setColumns(10);
>>>>>>> refs/remotes/origin/master
		
		this.textPane = new JTextPane();
		this.textPane.setBounds(149, 75, 455, 340);
		this.textPane.setBackground(new Color(220,220,220));
		this.textPane.setForeground(new Color(94,173,255));
		this.add(textPane);
		
		this.fileHistory = new JComboBox<String>();
		this.fileHistory.setBounds(245, 24, 120, 29);
		this.fileHistory.addItem("...");
		this.add(fileHistory);
		
<<<<<<< HEAD

		JButton btnCheckValidity = new JButton("Input File");
		btnCheckValidity.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
						textPane.setText("");
						String fileName = txtInputFileName.getText();
						try {
							BufferedReader br;
							br = new BufferedReader(
							new InputStreamReader(new FileInputStream(fileName)));
							textPane.setText(textPane.getText() + "File inputed\n\nPreview:\n");
			
							int count = 0;
							while(br.readLine() !=null)
							{
								count++;
							}
							br.close();
							String ary[] = new String[count];
							br = new BufferedReader(
									new InputStreamReader(new FileInputStream(fileName)));
							for(int i = 0; i< count; i++)
							{
								String toBeInserted = br.readLine();
								ary[i] = toBeInserted;
								textPane.setText(textPane.getText() + toBeInserted + "\n");
							}
							
							history.add(ary);
							historyFiles.add(fileName);
							fileHistory.addItem(fileName.substring(fileName.lastIndexOf('\\')+1));//remove all previous directories, show only file name
							
							
							File inFile = new File(fileName); // input file to be parsed
							NumberFormat doubleFormat = new DecimalFormat("#0.00");
							FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
							fileData.compute(); // uses the file to compute the data
							textPane.setText(textPane.getText()+ "File inputed.\nPlease make a selection to the left.\n");
							//System.out.println("Number of Lines: " + fileData.getNumLines());
							//System.out.println("Blank Lines: " + fileData.getBlankLines());
							//System.out.println("Number of Spaces: " + fileData.getNumSpaces());
							//System.out.println("Word Count: " + fileData.getNumWords());
							//System.out.println("Chars Per Line: " + doubleFormat.format(fileData.getCharsPerLine()));
							//System.out.println("Avg Word Length: " + doubleFormat.format(fileData.getAvgWordLen()));
							//System.out.println("Most common word " + fileData.getMostCommonWord());
							
							
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							textPane.setText(textPane.getText() + "File name not found\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		}				
		});
		btnCheckValidity.setBounds(209, 24, 103, 29);
		contentPane.add(btnCheckValidity);
		
		
		
		
		JButton btnNewButton = new JButton("Count Lines");
		btnNewButton.setBounds(0, 62, 131, 29);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e) {
								File inFile = new File(historyFiles.get(currFile)); // input file to be parsed
								NumberFormat doubleFormat = new DecimalFormat("#0.00");
								FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
								fileData.compute(); // uses the file to compute the data
								textPane.setText(textPane.getText()+"Line Count: "+ fileData.getNumLines()+"\n");
												
					}});
=======
		JLabel textPaneTitle = new JLabel("Console");
		textPaneTitle.setFont(new Font("garamond", Font.BOLD | Font.ITALIC, 20));
		textPaneTitle.setForeground(new Color(94,173,255));
		textPaneTitle.setBounds(335, 50, 150, 20);
		//contentPane.add(textPaneTitle);
>>>>>>> refs/remotes/origin/master
		
		JLabel functions = new JLabel("Functions");
		functions.setFont(new Font("garamond", Font.BOLD | Font.ITALIC, 20));
		functions.setForeground(new Color(94,173,255));
		functions.setBounds(19, 50, 150, 20);
		contentPane.add(functions);
		
<<<<<<< HEAD
		JButton btnBlankLines = new JButton("Blank Lines");
		btnBlankLines.setBounds(0, 119, 131, 29);
		contentPane.add(btnBlankLines);
		btnBlankLines.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e) {
								
								File inFile = new File(historyFiles.get(currFile)); // input file to be parsed
								NumberFormat doubleFormat = new DecimalFormat("#0.00");
								FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
								fileData.compute(); // uses the file to compute the data
								textPane.setText(textPane.getText()+"Blank Line Count: "+ fileData.getBlankLines()+"\n");
												
					}});
		
		JButton btnCountWords = new JButton("Count Words");
		btnCountWords.setBounds(0, 176, 131, 29);
		contentPane.add(btnCountWords);
		btnCountWords.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e) {
								
								File inFile = new File(historyFiles.get(currFile)); // input file to be parsed
								NumberFormat doubleFormat = new DecimalFormat("#0.00");
								FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
								fileData.compute(); // uses the file to compute the data
								textPane.setText(textPane.getText()+"Word Count: "+ fileData.getNumWords()+"\n");
												
					}});
		
		JButton btnCountSpaces = new JButton("Count Spaces");
		btnCountSpaces.setBounds(0, 233, 131, 29);
		contentPane.add(btnCountSpaces);
		btnCountSpaces.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e) {
								
								File inFile = new File(historyFiles.get(currFile)); // input file to be parsed
								NumberFormat doubleFormat = new DecimalFormat("#0.00");
								FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
								fileData.compute(); // uses the file to compute the data
								textPane.setText(textPane.getText()+"Space Count: "+ fileData.getNumSpaces()+"\n");
												
					}});
		
		JButton btnAvgCharline = new JButton("AVG Char/Line");
		btnAvgCharline.setBounds(0, 290, 131, 29);
		contentPane.add(btnAvgCharline);
		btnAvgCharline.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e) {

								File inFile = new File(historyFiles.get(currFile)); // input file to be parsed
								NumberFormat doubleFormat = new DecimalFormat("#0.00");
								FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
								fileData.compute(); // uses the file to compute the data
								textPane.setText(textPane.getText()+"Average Char/Line: "+ fileData.getCharsPerLine()+"\n");
												
					}});
		
		JButton btnAvgWordLength = new JButton("AVG Word Len");
		btnAvgWordLength.setBounds(0, 347, 131, 29);
		contentPane.add(btnAvgWordLength);
		btnAvgWordLength.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e) {
							
								File inFile = new File(historyFiles.get(currFile)); // input file to be parsed
								NumberFormat doubleFormat = new DecimalFormat("#0.00");
								FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
								fileData.compute(); // uses the file to compute the data
								textPane.setText(textPane.getText()+"Average Word Length: "+ fileData.getAvgWordLen()+"\n");
												
					}});
		
		JButton btnMostComWord = new JButton("Most Com Word");
		btnMostComWord.setBounds(0, 404, 131, 29);
		contentPane.add(btnMostComWord);
		btnMostComWord.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e) {
								File inFile = new File(historyFiles.get(currFile)); // input file to be parsed
								NumberFormat doubleFormat = new DecimalFormat("#0.00");
								FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
								fileData.compute(); // uses the file to compute the data
								textPane.setText(textPane.getText()+"Most Common Word: "+ fileData.getMostCommonWord()+"\n");
												
					}});
=======
		this.combineFiles = new JList<String>();
		//combineFiles.setBounds(0, 0, 20, 20);
		combineFiles.setLayoutOrientation(JList.VERTICAL);
		combineFiles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		
		JScrollPane listScroll = new JScrollPane(combineFiles);
		listScroll.setPreferredSize(new Dimension(90,50));
		listScroll.setBounds(385, 1, 90, 50);
		this.add(listScroll);
		
		
		
	try {		
		this.combineBtn = new JButton("Combine");
		this.combineBtn.setForeground(new Color(94,173,255));
		this.combineBtn.addActionListener(this);
		this.combineBtn.setBounds(374, 50, 115, 29);
		this.add(combineBtn);
		
		this.btnCheckValidity = new JButton("Insert");
		this.btnCheckValidity.setContentAreaFilled(false);
		this.btnCheckValidity.setBorder(BorderFactory.createEmptyBorder());
		this.btnCheckValidity.setForeground(new Color(94,173,255));		
		this.btnCheckValidity.addActionListener(this);
		this.btnCheckValidity.setBounds(105, 16, 115, 29);
		this.add(btnCheckValidity);
>>>>>>> refs/remotes/origin/master
		
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
		this.btnMostComWord.setBounds(3, 315, 131, 29);
		this.add(btnMostComWord);
		this.btnMostComWord.addActionListener(this);
		
		JLabel lblHelp = new JLabel("Help");
		lblHelp.setFont(new Font("garamond", Font.BOLD, 14));
		lblHelp.setForeground(new Color(94,173,255));
		lblHelp.setBounds(539, 7, 40, 15);
		contentPane.add(lblHelp);
		
<<<<<<< HEAD
		JLabel btnHistory = new JLabel("Selected File");
		btnHistory.setBounds(332, 0, 120, 29);
		contentPane.add(btnHistory);
		fileHistory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox box = (JComboBox) e.getSource();
				for(int i = 0; i<historyFiles.size();i++){
					String file = historyFiles.get(i);
					textPane.setText("");
					if(box.getSelectedItem().equals(file)){
						String[] text = history.get(i);
						currFile = i;
						textPane.setText("Preview:\n");
						for(int j = 0; j<text.length;j++){
							textPane.setText(textPane.getText() + text[j] + "\n");
						}
						break;
					}
				}
			}
		});
		
		
=======
		String[] helpItems = {"...", "File Input", "Calculations", "Multiple File Input"};
		JComboBox<String> cbxHelp = new JComboBox<String>(helpItems);
		cbxHelp.setBounds(500, 24, 114, 29);
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
>>>>>>> refs/remotes/origin/master
		
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
		 					String file = historyFiles.get(i).getFile().toString();
		 					file = file.substring(file.lastIndexOf('\\')+1);
		 					textPane.setText("");
		 					if(box.getSelectedItem().equals(file)){
		 						String[] text = history.get(i);
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
								
				for(int i = 0; i< count; i++)//insert the strings into the array
				{
					String toBeInserted = br.readLine();
					ary[i] = toBeInserted;
					textPane.setText(textPane.getText() + toBeInserted +"\n");
				}
				history.add(ary);

				File inFile = new File(fileName); // input file to be parsed
				NumberFormat doubleFormat = new DecimalFormat("#0.00");
				FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
				fileData.compute(); // uses the file to compute the data
				historyFiles.add(fileData);
				fileHistory.addItem(fileName.substring(fileName.lastIndexOf('\\')+1));//remove all previous directories, show only file name
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				textPane.setText(textPane.getText() + "File name not found\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else{
			FileParser file = historyFiles.get(currFile);
			if (e.getSource() == btnNewButton) {
				textPane.setText(textPane.getText()+"Line Count: "+ file.getNumLines()+"\n");
			} else if(e.getSource() == btnBlankLines){
				textPane.setText(textPane.getText()+"Blank Line Count: "+ file.getBlankLines()+"\n");
			} else if(e.getSource() == btnCountWords){
				textPane.setText(textPane.getText()+"Word Count: "+ file.getNumWords()+"\n");
			} else if(e.getSource() == btnCountSpaces){
				textPane.setText(textPane.getText()+"Space Count: "+ file.getNumSpaces()+"\n");
			} else if(e.getSource() == btnAvgCharline){
				textPane.setText(textPane.getText()+"Average Char/Line: "+ file.getCharsPerLine()+"\n");		
			} else if(e.getSource() == btnAvgWordLength){
				textPane.setText(textPane.getText()+"Average Word Length: "+ file.getAvgWordLen()+"\n");
			} else if(e.getSource() == btnMostComWord){
				textPane.setText(textPane.getText()+"Most Common Word: "+ file.getMostCommonWord()+"\n");
			}
		}
	}
}
package test;
import java.io.*;
import java.util.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
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

public class builderTest extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputFileName;

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
		LinkedList<String[]> history = new LinkedList<String[]>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Text Analyzer");
		lblNewLabel.setBounds(179, 6, 91, 16);
		contentPane.add(lblNewLabel);
		
		txtInputFileName = new JTextField();
		txtInputFileName.setForeground(Color.GRAY);
		txtInputFileName.setText("Input File Name...");
		txtInputFileName.setBounds(6, 24, 207, 26);
		contentPane.add(txtInputFileName);
		txtInputFileName.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(149, 62, 465, 363);
		contentPane.add(textPane);
		
		JButton btnCheckValidity = new JButton("Input File");
		btnCheckValidity.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
						textPane.setText("");
						String fileName = txtInputFileName.getText();
						try {
							BufferedReader br;
							br = new BufferedReader(
							new InputStreamReader(new FileInputStream(fileName)));
							textPane.setText(textPane.getText() + "File inputed\n\nPlease make a Selection\n");
							
			
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
								textPane.setText(textPane.getText() + toBeInserted +"\n");
							}
							
							
							
							
							File inFile = new File(fileName); // input file to be parsed
							NumberFormat doubleFormat = new DecimalFormat("#0.00");
							FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
							fileData.compute(); // uses the file to compute the data
							System.out.println("Number of Lines: " + fileData.getNumLines());
							System.out.println("Blank Lines: " + fileData.getBlankLines());
							System.out.println("Number of Spaces: " + fileData.getNumSpaces());
							System.out.println("Word Count: " + fileData.getNumWords());
							System.out.println("Chars Per Line: " + doubleFormat.format(fileData.getCharsPerLine()));
							System.out.println("Avg Word Length: " + doubleFormat.format(fileData.getAvgWordLen()));
							System.out.println("Most common word: " + fileData.getMostCommonWord());
							
							
							
				
							history.add(ary);
							
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
										File inFile = new File(txtInputFileName.getText()); // input file to be parsed
										NumberFormat doubleFormat = new DecimalFormat("#0.00");
										FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
										fileData.compute(); // uses the file to compute the data
										textPane.setText(textPane.getText()+"Line Count: "+ fileData.getNumLines()+"\n");
														
							}});
				
				
				JButton btnBlankLines = new JButton("Blank Lines");
				btnBlankLines.setBounds(0, 119, 131, 29);
				contentPane.add(btnBlankLines);
				btnBlankLines.addActionListener(new ActionListener(){ 
							public void actionPerformed(ActionEvent e) {
										
										File inFile = new File(txtInputFileName.getText()); // input file to be parsed
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
										
										File inFile = new File(txtInputFileName.getText()); // input file to be parsed
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
										
										File inFile = new File(txtInputFileName.getText()); // input file to be parsed
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

										File inFile = new File(txtInputFileName.getText()); // input file to be parsed
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
									
										File inFile = new File(txtInputFileName.getText()); // input file to be parsed
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
										File inFile = new File(txtInputFileName.getText()); // input file to be parsed
										NumberFormat doubleFormat = new DecimalFormat("#0.00");
										FileParser fileData = new FileParser(inFile);	// parsing object with all necessary data
										fileData.compute(); // uses the file to compute the data
										textPane.setText(textPane.getText()+"Most Common Word: "+ fileData.getMostCommonWord()+"\n");
														
							}});
		
		JLabel lblHelp = new JLabel("Help:");
		lblHelp.setBounds(500, 9, 40, 15);
		contentPane.add(lblHelp);
		
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
		contentPane.add(cbxHelp);
		
		JButton btnHistory = new JButton("File History");
		btnHistory.setBounds(332, 24, 120, 29);
		contentPane.add(btnHistory);
		
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
}
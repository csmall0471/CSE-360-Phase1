package test;
import java.io.*;
import java.util.*;
import java.awt.BorderLayout;
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
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class builderTest extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputFileName;
	int currFile;

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
		LinkedList<String> historyFiles = new LinkedList<String>();
		currFile = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> fileHistory = new JComboBox<String>();
		fileHistory.setBounds(332, 24, 120, 29);
		contentPane.add(fileHistory);
		
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
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(523, 6, 91, 29);
		contentPane.add(btnHelp);
		
		
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

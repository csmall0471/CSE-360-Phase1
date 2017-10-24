package test;
import java.io.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
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
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

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
								ary[i]=toBeInserted;
								textPane.setText(textPane.getText() + toBeInserted +"\n");
							}
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
		
		JButton btnBlankLines = new JButton("Blank Lines");
		btnBlankLines.setBounds(0, 119, 131, 29);
		contentPane.add(btnBlankLines);
		
		JButton btnCountWords = new JButton("Count Words");
		btnCountWords.setBounds(0, 176, 131, 29);
		contentPane.add(btnCountWords);
		
		JButton btnCountSpaces = new JButton("Count Spaces");
		btnCountSpaces.setBounds(0, 233, 131, 29);
		contentPane.add(btnCountSpaces);
		
		JButton btnAvgCharline = new JButton("AVG Char/Line");
		btnAvgCharline.setBounds(0, 290, 131, 29);
		contentPane.add(btnAvgCharline);
		
		JButton btnAvgWordLength = new JButton("AVG Word Len");
		btnAvgWordLength.setBounds(0, 347, 131, 29);
		contentPane.add(btnAvgWordLength);
		
		JButton btnMostComWord = new JButton("Most Com Word");
		btnMostComWord.setBounds(0, 404, 131, 29);
		contentPane.add(btnMostComWord);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(523, 6, 91, 29);
		contentPane.add(btnHelp);
		
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
/*
 * This is everything to do with the load tab of the GUI
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LoadTab extends mainFrame implements ActionListener
{
	JButton load;
	
	LoadTab()
	{
		//basic setup for the load button
		load = new JButton();
		load = loadButton;
		
		loadButton.setBounds(0,100,200,100); //(x, y, width, height)
		loadButton.addActionListener(this);
		loadButton.setFocusable(false);
		loadButton.setText("Load Data");
		
	
	}

	/*
	 * When the button is clicked, this is where we tell the program what to do.
	 */
	public void actionPerformed(ActionEvent e) 
	{

		File file = null;
		String line = "";
		
		if(e.getSource() == load)
		{
			/*
			 * This section changes the visibility of the different panels accordingly, 
			 * allowing us to "switch" between panels
			 */
			aboutPanel.setVisible(false);
			addPanel.setVisible(false);
			visualizePanel.setVisible(false);
			scrollPane.setVisible(true);
			
			JFileChooser fileChooser = new JFileChooser();
			
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) //if 'open' is clicked in the file explorer, won't execute if file explorer is closed or 'cancel' is clicked
			{
				
				
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());	//this uses the file explorer to choose a file and save its path
				
				/*
				 * potentially need to implement a way to check if file extension is .csv in order to continue
				 */
				
				
				/*
				 * This section saves the contents of the excel file into the 'lines', which is then turned into
				 * the 2d array 'array'. We can use this array to access individual cells within the .csv file
				 */
				try {
					
					BufferedReader br = new BufferedReader(new FileReader(file));

					ArrayList<String[]> lines = new ArrayList<String[]>();
					while((line = br.readLine()) != null)
					{
						lines.add(line.split(","));
					}
					
					String[][] array = new String[lines.size()][0]; //this is where we make it into a 2d array
					lines.toArray(array);
					
					String[] columnNames = new String[array[0].length]; //create an array of the column Names
					
					for(int i = 0; i < array[0].length; i++) //save the column names in the .csv file to the columnNames array
					{
						columnNames[i] = array[0][i].toString();
					}
					
					mainFrame test = new mainFrame();
					
					test.setData(array, columnNames);
				
				}
				catch (FileNotFoundException f) {
					f.printStackTrace();
				}
				catch (IOException f) {
					f.printStackTrace();
				}
				
				
			}
			
		}
	}
}

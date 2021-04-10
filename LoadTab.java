/*
 * This is everything to do with the load tab of the GUI
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadTab extends mainFrame implements ActionListener
{
	
	LoadTab()
	{
		//basic setup for the load button
		
		loadButton.setBounds(0,100,200,100); //(x, y, width, height)
		loadButton.addActionListener(this);
		loadButton.setFocusable(false);
		loadButton.setText("Load Data");
		
	
	}

	/*
	 * When the button is clicked, this is where we tell the program what to do.
	 */
	@SuppressWarnings("resource")
	public void actionPerformed(ActionEvent e) 
	{

		File file = null;
		String line = "";
		
		if(e.getSource() == loadButton)
		{
			/*
			 * This section changes the visibility of the different panels accordingly, 
			 * allowing us to "switch" between panels
			 */
			aboutPanel.setVisible(false);
			addPanel.setVisible(false);
			visualizePanel.setVisible(false);
			scrollPane.setVisible(true);
			
			/*
			 * Here we create the fileChooser that allows us to search for and select a .csv file to load in. The fileChooser implements a filter that allows for only .csv files to be chosen
			 */
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (.csv)", "csv"); //this is the filter that we're going to use to ensure that only .csv files can be chosen
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false); //this is so that we can't select "all files" and choose any type of file
			
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) //if 'open' is clicked in the file explorer, won't execute if file explorer is closed or 'cancel' is clicked
			{
				
				
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());	//this uses the file explorer to choose a file and save its path
							
				/*
				 * This section saves the contents of the excel file into the 'lines' ArrayList, which is then turned into
				 * the 2d array 'array'. We can use this array to access individual cells within the .csv file
				 * 
				 * Since JTable works by taking in the ColumnNames and the actual data separately, we actually need to separate the columnNames from
				 * the CSV file into their own string array
				 * 
				 * We accomplish this by looping through the first row and taking all of the columnNames. We place these into their own separate columnNames string array. 
				 * Then we actually have to create a new array and copy everything over except for the first row (the actual data array). This is done in order to make sure that the data shown in our array
				 * doesn't show the column names twice.
				 */
				try {
					//here is where we extract the data from the csv file into the 'lines' ArrayList
					BufferedReader br = new BufferedReader(new FileReader(file));

					ArrayList<String[]> lines = new ArrayList<String[]>();
					while((line = br.readLine()) != null)
					{
						lines.add(line.split(","));
					}
					
					String[][] array = new String[lines.size()][0];
					lines.toArray(array);	//here we convert the 'lines' ArrayList (which has all of the csv file data) into a 2d array
					
					String[] columnNames = new String[array[0].length]; //here we create an array of the column names
					
					for(int i = 0; i < array[0].length; i++) //loop through the columns and save them into the columnNames array
					{
						columnNames[i] = array[0][i].toString();
					}
					
					/*
					 * creates a new array that has one less row than the unfiltered csv file array. This is going to be the array with the actual data.
					 * We copy everything over starting from the unfiltered array's first line, avoiding the column names.
					 */
					String[][] shortenedArray = new String[array.length-1][6];
					for(int y = 0; y < shortenedArray.length; y++)
					{
						for(int x = 0; x < shortenedArray[0].length; x++)
						{
							shortenedArray[y][x] = array[y+1][x];
						}
					}						
					
					/*
					 * This section appends the data to the current array
					 */
					mainFrame temp = new mainFrame();
					
					String[][] newArray = new String[shortenedArray.length + data.length][6];
					System.arraycopy(data, 0, newArray, 0, data.length);
					System.arraycopy(shortenedArray, 0, newArray, data.length, shortenedArray.length);
					
					temp.setData(newArray, columnNames); //update the array of data with the new data
				
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

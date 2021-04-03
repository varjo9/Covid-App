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
import javax.swing.JButton;
import javax.swing.JFileChooser;

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
				 * 
				 * Since JTable works by taking in the ColumnNames and the actual data separately, we actually need to seperate the columnNames from
				 * the CSV file into their own string array
				 * 
				 * We accomplish this by looping through the first row and taking all of the columnNames. We place these into their own seperate columnNames string array. 
				 * Then we actually have to create a new array and copy everything over except for the first row. This is done in order to make sure that the data shown in our array
				 * doesn't show the column names twice.
				 */
				try {
					//here is where we attempt to extract the data from the csv file into the 'lines' ArrayList
					BufferedReader br = new BufferedReader(new FileReader(file));

					ArrayList<String[]> lines = new ArrayList<String[]>();
					while((line = br.readLine()) != null)
					{
						lines.add(line.split(","));
					}
					
					String[][] array = new String[lines.size()][0]; //here we convert the 'lines' ArrayList (which has all of the csv file data) into a 2d array
					lines.toArray(array);
					
					String[] columnNames = new String[array[0].length]; //here we create an array of the column names
					
					for(int i = 0; i < array[0].length; i++) //loop through the columns and save them into the columnNames array
					{
						columnNames[i] = array[0][i].toString();
					}
					
					/*
					 * creates a new array that has one less row than the unfiltered csv file array
					 * we copy everything over starting from the unfiltered array's first line, avoiding the column names
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
					 * This section appends the input to the current array
					 */
					mainFrame test = new mainFrame();
					
					String[][] newArray = new String[shortenedArray.length + data.length][6];
					System.arraycopy(data, 0, newArray, 0, data.length);
					System.arraycopy(shortenedArray, 0, newArray, data.length, shortenedArray.length);
					
					test.setData(newArray, columnNames); //set the array to the new data
				
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

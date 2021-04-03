/*
 * This is everything to do with the save tab of the GUI
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class SaveTab extends mainFrame implements ActionListener
{
	JButton save;
	
	SaveTab()
	{
		//basic setup for the save button
		save = new JButton();
		save = saveButton;
		
		saveButton.setBounds(0,300,200,100); //(x, y, width, height)
		saveButton.addActionListener(this);
		saveButton.setFocusable(false);
		saveButton.setText("Save Data");
		
	}

	/*
	 * When the button is clicked, this is where we tell the program what to do.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		/*
		 * Program opens up a FileChooser, where a user can choose a file location and give a file name.
		 * If the user presses 'save' in the file explorer, the current array data will be written into a csv file 
		 * at that location
		 */
		if(e.getSource() == save)
		{
			JFileChooser fileChooser = new JFileChooser();
			String fileLocation = "";
			
			int response = fileChooser.showSaveDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION)	//if the user clicked 'save' in the file explorer (did not exit or close out of it)
			{
				//this section gets the file location chosen by the user and converts it into the fileLocation string
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				fileLocation = file.toString();
				fileLocation = fileLocation + ".csv"; //add .csv to the fileLocation string to ensure that we're saving into a .csv file
				
				//this section of code takes the array data that we have and uses a StringBuilder to convert it into a format that a .csv file can take in (comma separated values)
				// \r\n = newline
				// ',' = next cell
				try 
				{
					PrintWriter pw = new PrintWriter(new File(fileLocation));
					StringBuilder sb = new StringBuilder();
				
					for(int i = 0; i < columnNames.length; i++) //append the column names first
					{
						sb.append(columnNames[i]);
						sb.append(",");
					}
					
					sb.append("\r\n");
					
					for(int j = 0; j < data.length; j++) //afterwards, append the actual data
					{
						for(int k = 0; k < data[0].length; k++)
						{
							sb.append(data[j][k]);
							sb.append(",");
						}
						sb.append("\r\n");
					}
					
					pw.write(sb.toString());
					pw.close();
					
				}
				catch(Exception g) {
					
				}
			}
				
		}
	}
}

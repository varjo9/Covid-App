/*
 * This is everything to do with the add tab of the GUI
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTab extends mainFrame implements ActionListener
{
	
	AddTab()
	{
		//basic setup for the add button
		addButton.setBounds(0,200,200,100); //(x, y, width, height)
		addButton.addActionListener(this);
		addButton.setFocusable(false);
		addButton.setText("Add Data");
		
		//setup for the submit button
		submitButton.setBounds(200, 250, 100, 25);
		submitButton.addActionListener(this);
		submitButton.setFocusable(false);
		submitButton.setText("SUBMIT");
		
		
		//basic setup for the add panel
		addPanel.setOpaque(false);
		addPanel.setBounds(200,0,500,500);
		addPanel.setVisible(false);
		
	}

	/*
	 * When the button is clicked, this is where we tell the program what to do.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		mainFrame frame = new mainFrame();
		
		if(e.getSource() == addButton)	//if add button is clicked
		{
			/*
			 * This section changes the visibility of the different panels accordingly, 
			 * allowing us to "switch" between panels
			 */
			aboutPanel.setVisible(false);
			addPanel.setVisible(true);
			visualizePanel.setVisible(false);
			scrollPane.setVisible(false);
			
		}
		if (e.getSource() == submitButton)	//if submitButton is clicked
		{
		
			boolean copy = false;
			
			/*
			 * this grabs all of the text in the textFields and gives them to the input String array
			 */
			String[][] input = new String[1][6]; //input string where we'll store the textField inputs
			
			input[0][0] = idInput.getText();
			input[0][1] = lastNameInput.getText();
			input[0][2] = firstNameInput.getText();
			input[0][3] = vTypeInput.getText();
			input[0][4] = vDateInput.getText();
			input[0][5] = vLocationInput.getText();
			
			/*
			 * This section appends the input to the current array
			 */
			String[][] newArray = new String[input.length + data.length][6];
			System.arraycopy(data, 0, newArray, 0, data.length);
			System.arraycopy(input, 0, newArray, data.length, input.length);
			
			
			/*
			 * here we're going to check if the information we're trying to submit includes a duplicate ID. If there is a duplicate ID, then we make sure that it isn't added to the
			 * data
			 */
			if(data.length != 0)
			{
				for (int i = 0; i < data.length; i++)
				{
					if(data[i][0].equals(input[0][0]))
					{
						copy = true;	//this sets our copy boolean to true, indicating that the submitted input includes a duplicate ID
					}
				}
			}
			if(copy == false)
			{
				frame.setData(newArray, columnNames); //updateArray to include the new Data
			}
			
			
			//this section resets all of the text fields to empty once submit is pressed
			idInput.setText("");
			lastNameInput.setText("");
			firstNameInput.setText("");
			vTypeInput.setText("");
			vDateInput.setText("");
			vLocationInput.setText("");
		}
	}
}

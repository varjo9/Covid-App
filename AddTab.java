/*
 * This is everything to do with the add tab of the GUI
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.util.*;

public class AddTab extends mainFrame implements ActionListener
{
	JButton add;
	JButton submit;
	
	AddTab()
	{
		//basic setup for the add button
		add= new JButton();
		add = addButton;
		
		addButton.setBounds(0,200,200,100); //(x, y, width, height)
		addButton.addActionListener(this);
		addButton.setFocusable(false);
		addButton.setText("Add Data");
		
		//setup for the submit button
		submit = new JButton();
		submit = submitButton;
		
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
		
		if(e.getSource() == add)     //whenever the add button is clicked
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
		if (e.getSource() == submitButton)
		{
			System.out.println("Submit clicked");	//whenever the submit button is clicked
		
			/*
			 * this grabs all of the text in the textFields and gives them to the input String array
			 */
			String[][] input = new String[1][6];
			
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
			
			frame.setData(newArray, columnNames); //updateArray to include the new Data
			
			//this section resets all of the text fields once submit is pressed
			idInput.setText("");
			lastNameInput.setText("");
			firstNameInput.setText("");
			vTypeInput.setText("");
			vDateInput.setText("");
			vLocationInput.setText("");
		}
	}
}

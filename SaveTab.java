/*
 * This is everything to do with the save tab of the GUI
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
		if(e.getSource() == save)
		{
			
		}
	}
}

/*
 * This is everything to do with the about tab of the GUI
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutTab extends mainFrame implements ActionListener
{	
	AboutTab()
	{
		//setup for the aboutButton
		aboutButton.setBounds(0,0,200,100); //(x, y, width, height)
		aboutButton.addActionListener(this);
		aboutButton.setFocusable(false);
		aboutButton.setText("About");	
		
		//basic setup for the about panel
		aboutPanel.setBounds(200,0,500,500);
		aboutPanel.setVisible(false);	
	}

	/*
	 * When the button is clicked, this is where we tell the program what to do.
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == aboutButton)
		{
			/*
			 * This section changes the visibility of the different panels accordingly, 
			 * allowing us to "switch" between panels
			 */
			aboutPanel.setVisible(true);
			addPanel.setVisible(false);
			visualizePanel.setVisible(false);
			scrollPane.setVisible(false);
			
		}		
	}
}


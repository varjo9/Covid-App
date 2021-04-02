/*
 * This is everything to do with the visualize tab of the GUI
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class VisualizeTab extends mainFrame implements ActionListener
{
	JButton visualize;
	
	VisualizeTab()
	{
		//basic setup for the visualize button
		visualize = new JButton();
		visualize = visualizeButton;
		
		visualizeButton.setBounds(0,400,200,100); //(x, y, width, height)
		visualizeButton.addActionListener(this);
		visualizeButton.setFocusable(false);
		visualizeButton.setText("Visualize Data");
		
		//basic setup for the visualize panel
		visualizePanel.setOpaque(false);
		visualizePanel.setBounds(200,0,500,500);
		visualizePanel.setVisible(false);
		
	}

	/*
	 * When the button is clicked, this is where we tell the program what to do.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == visualize)
		{
			/*
			 * This section changes the visibility of the different panels accordingly, 
			 * allowing us to "switch" between panels
			 */
			aboutPanel.setVisible(false);
			addPanel.setVisible(false);
			visualizePanel.setVisible(true);
			scrollPane.setVisible(false);
		}
	}
}

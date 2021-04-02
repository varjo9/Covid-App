/*
 * This is everything to do with the about tab of the GUI
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class AboutTab extends mainFrame implements ActionListener
{	
	JButton about; //about button
	JLabel info;
	JTextArea desc;
	boolean created = false;

	AboutTab()
	{
		//basic setup for the about button
		about = new JButton();
		about = aboutButton;
		
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
		if(e.getSource() == about)
		{
			/*
			 * This section changes the visibility of the different panels accordingly, 
			 * allowing us to "switch" between panels
			 */
			aboutPanel.setVisible(true);
			addPanel.setVisible(false);
			visualizePanel.setVisible(false);
			scrollPane.setVisible(false);
			
			
			if(created == false)
			{
				desc = new JTextArea("CREATED BY:\n" +
					    "Team #42\n" +
					    "Alexis Correa\n" +
					    "Michael Moore\n" +
					    "Anh Pham\n" +
					    "Thi Viet Chinh Ngo"
					);
				desc.setLineWrap(false);
				desc.setWrapStyleWord(true);
				aboutPanel.add(desc);
				created = true;
			}
			
			
		}

	}
}

/*
 * This is everything to do with the visualize tab of the GUI
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizeTab extends mainFrame implements ActionListener
{
	
	VisualizeTab()
	{
		//basic setup for the visualize button
		visualizeButton.setBounds(0,400,200,100); //(x, y, width, height)
		visualizeButton.addActionListener(this);
		visualizeButton.setFocusable(false);
		visualizeButton.setText("Visualize Data");
		
		//adding actionListeners to the bar & pie buttons so that we can give them actions
		barButton.addActionListener(this);
		pieButton.addActionListener(this);
		
		//basic setup for the visualize panel
		visualizePanel.setVisible(false);
		
	}

	/*
	 * When the button is clicked, this is where we tell the program what to do.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == visualizeButton)
		{
			/*
			 * This section changes the visibility of the different panels accordingly, 
			 * allowing us to "switch" between panels
			 */
			aboutPanel.setVisible(false);
			addPanel.setVisible(false);
			visualizePanel.setVisible(true);
			scrollPane.setVisible(false);
			
			/*
			 * The goal is to extract the necessary data for both graphs here. The data needed for the bar chart is every unique
			 * vaccine type, and then the amount of times every unique type appears in the data. The data needed for the pie chart is every
			 * unique location and the amount of times every location appears in the data.
			 * 
			 * To do this, we loop through the data and add every unique cell to an arrayList. Once that's done, we loop through the data again figure out how many times each unique cell is 
			 * repeated. This effectively gives us the amount of doses administered for every unique element.
			 * 
			 * It's the same process for both the bar chart data and the pie chart data
			 */
			
			/*
			 * here we create an array list with every unique vaccine type in our current data set
			 */
			ArrayList<String> types = new ArrayList<String>();	
			
			for(int i = 0; i < data.length; i++)
			{
				if(types.contains(data[i][3]) == false)	//this is where we isolate all of the unique data types
				{
					types.add(data[i][3]);
				}
			}
			
			//same thing but for unique locations
			ArrayList<String> locations = new ArrayList<String>();	
			
			for(int i = 0; i < data.length; i++)
			{
				if(locations.contains(data[i][5]) == false)	//this is where we isolate all of the unique data types
				{
					locations.add(data[i][5]);
				}
			}
			
			/*
			 * Here we figure out how many times every unique vaccine is repeated in the data, giving us a count of how many
			 * doses have been administered for each type
			 */
			int[] barValues = new int[types.size()];
			
			for(int j = 0; j < data.length; j++)
			{
				for(int k = 0; k < types.size(); k++)
				{
					if(types.get(k).equals(data[j][3]))
					{
						barValues[k]++;
					}
				}
			}
			
			//same thing but for pie values (number of vaccines administered per location)
			int[] pieValues = new int[locations.size()];
			
			for(int j = 0; j < data.length; j++)
			{
				for(int k = 0; k < locations.size(); k++)
				{
					if(locations.get(k).equals(data[j][5]))
					{
						pieValues[k]++;
					}
				}
			}
			
			/*
			 * here we add the update the bar chart with the data we've acquired. We add all of the unique vaccine types, and then their corresponding amount
			 * of doses.
			 */
			for(int x = 0; x < types.size(); x++)
			{
				barChartData.setValue(barValues[x], "Number of Doses Administered", types.get(x).toString());
			}
			
			//same thing but update the pie chart instead
			for(int x = 0; x < locations.size(); x++)
			{
				pieChartData.setValue(locations.get(x), pieValues[x]);
			}

		}
		
		if(e.getSource() == barButton)	//if barButton is pressed, make the bar chart visible and the pie chart invisible
		{
			barPanel.setVisible(true);
			piePanel.setVisible(false);
		}
		if(e.getSource() == pieButton)	//if the pieButton is pressed, make the pie chart visible and the bar chart invisible
		{
			barPanel.setVisible(false);
			piePanel.setVisible(true);
		}
	}
}

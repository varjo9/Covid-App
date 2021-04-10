/*
 * This is meant to be where we create and put together all of the major elements in the GUI.
 * The frame does not have a layout, elements are placed manually
 */


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.text.DecimalFormat;


public class mainFrame 
{
	//frame + all of the panels that will contain content and the table
	public static JFrame frame;
	public static JPanel aboutPanel;
	public static JPanel addPanel;
	public static JPanel visualizePanel;
	public static JPanel graphPanel;
	public static JTable table;
	public static JScrollPane scrollPane;
	
	//text area that will be in our about tab
	public static JTextArea desc;

	//where we'll store all of the data needed for our table + graphs
	static String[] columnNames = {"ID","Last Name","First Name", "Vaccine Type", "Vaccine Date", "Vaccine Location"}; //table's columns
	static String[][] data = {}; //rest of tables data
	
	//labels in our add panel
	private static JLabel idLabel;
	private static JLabel lastNameLabel;
	private static JLabel firstNameLabel;
	private static JLabel vTypeLabel;
	private static JLabel vDateLabel;
	private static JLabel vLocationLabel;
	
	//textfields in our add panel
	public static JTextField idInput;
	public static JTextField lastNameInput;
	public static JTextField firstNameInput;
	public static JTextField vTypeInput;
	public static JTextField vDateInput;
	public static JTextField vLocationInput;
	
	//submit button in our add panel
	public static JButton submitButton;
	
	//all of the buttons on the side that will let us switch between panels
	public static JButton aboutButton;
	public static JButton loadButton;
	public static JButton addButton;
	public static JButton saveButton;
	public static JButton visualizeButton;
	
	//the two buttons in the visualize panel that will allow us to switch between graphs
	public static JButton barButton;
	public static JButton pieButton;
		
	//needed to create bar chart
	public static DefaultCategoryDataset barChartData;
	public static JFreeChart barChart;
	public static CategoryPlot barChartPlot;
	public static ChartPanel barPanel;
	
	//needed to create pie chart
	public static DefaultPieDataset pieChartData;
	public static JFreeChart pieChart;
	public static CategoryPlot pieChartPlot;
	public static ChartPanel piePanel;
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{	
		//basic frame setup
		
		frame = new JFrame(); //creates a frame
		frame.setTitle("Main Screen"); //set title of frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
		frame.setResizable(false); //prevent from being resized
		frame.setSize(700,525);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
			
		/*
		 * Creates all of the panels (except the scrollPanel, which is added later with the table) and adds 
		 * them to the frame
		 */
		aboutPanel = new JPanel();
		aboutPanel.setBounds(200, 0, 500, 500);
		frame.getContentPane().add(aboutPanel);
		
		addPanel = new JPanel();
		addPanel.setBounds(200, 0, 500, 500);
		addPanel.setLayout(null);
		frame.getContentPane().add(addPanel);
		
		visualizePanel = new JPanel();
		visualizePanel.setBounds(200, 0, 500, 500);
		frame.getContentPane().add(visualizePanel);
		visualizePanel.setLayout(null);
		
		graphPanel = new JPanel();
		graphPanel.setBounds(0,0,490,450);
		graphPanel.setLayout(null);
		visualizePanel.add(graphPanel);

		
		/*
		 * Creates all of the buttons and adds them to the frame
		 */
		aboutButton = new JButton();
		frame.getContentPane().add(aboutButton);
		
		loadButton = new JButton();
		frame.getContentPane().add(loadButton);
		
		addButton = new JButton();
		frame.getContentPane().add(addButton);
		
		saveButton = new JButton();
		frame.getContentPane().add(saveButton);
		
		visualizeButton = new JButton();
		frame.getContentPane().add(visualizeButton);
		
		/*
		 * Here we create the textArea that will be added to the about tab with all of our team information
		 */
		desc = new JTextArea("CREATED BY:\n" +
			    "Team #42\n" +
			    "Alexis Correa\n" +
			    "Michael Moore\n" +
			    "Anh Pham\n" +
			    "Thi Viet Chinh Ngo"
			);
		desc.setLineWrap(false);
		desc.setWrapStyleWord(true);
		desc.setEditable(false);
		aboutPanel.add(desc);
		
		/*
		 * Creates all of the addPanel's labels and adds them to the addPanel
		 */
		idLabel = new JLabel("ID:");
		idLabel.setBounds(145, 125, 120, 20);
		addPanel.add(idLabel);
		
		lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(145, 145, 120, 20);
		addPanel.add(lastNameLabel);
		
		firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(145, 165, 120, 20);
		addPanel.add(firstNameLabel);
		
		vTypeLabel = new JLabel("Vaccine Type:");
		vTypeLabel.setBounds(145, 185, 120, 20);
		addPanel.add(vTypeLabel);
		
		vDateLabel = new JLabel("Vaccination Date:");
		vDateLabel.setBounds(145, 205, 120, 20);
		addPanel.add(vDateLabel);
		
		vLocationLabel = new JLabel("Vaccine Location:");
		vLocationLabel.setBounds(145, 225, 120, 20);
		addPanel.add(vLocationLabel);
		
		/*
		 * Here we create all of the textFields that accompany the labels and add them to the addPanel
		 */
		idInput = new JTextField();
		idInput.setBounds(255, 125, 100, 20);
		addPanel.add(idInput);
		idInput.setColumns(10);
		
		lastNameInput = new JTextField();
		lastNameInput.setColumns(10);
		lastNameInput.setBounds(255, 145, 100, 20);
		addPanel.add(lastNameInput);
		
		firstNameInput = new JTextField();
		firstNameInput.setColumns(10);
		firstNameInput.setBounds(255, 165, 100, 20);
		addPanel.add(firstNameInput);
		
		vTypeInput = new JTextField();
		vTypeInput.setColumns(10);
		vTypeInput.setBounds(255, 185, 100, 20);
		addPanel.add(vTypeInput);
		
		vDateInput = new JTextField();
		vDateInput.setColumns(10);
		vDateInput.setBounds(255, 205, 100, 20);
		addPanel.add(vDateInput);
		
		vLocationInput = new JTextField();
		vLocationInput.setColumns(10);
		vLocationInput.setBounds(255, 225, 100, 20);
		addPanel.add(vLocationInput);
		
		//submitButton is created and added to the addPanel
		submitButton = new JButton();
		addPanel.add(submitButton);
		
		/*
		 * Here we create and initialize the JTable, and we add it to the ScrollPane
		 * The ScrollPane is then added to the frame
		 */
		
		table = new JTable(data, columnNames);
		table.setBorder(new LineBorder(Color.GRAY, 1));
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(data, columnNames));
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
			
			//ScrollPane part
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(200, 0, 500, 500);
		frame.getContentPane().add(scrollPane);
		
		/*
		 * Here we create the two buttons inside the visualize panel that will allow us to switch between the bar char and the pie chart;
		 */
		barButton = new JButton();	//bar chart button
		barButton.setSize(100, 25);
		barButton.setLocation(125, 462);
		barButton.setText("Bar Chart");
		barButton.setFocusable(false);
		visualizePanel.add(barButton);
			
		pieButton = new JButton();	//pie chart button
		pieButton.setLocation(275, 462);
		pieButton.setSize(100, 25);
		pieButton.setText("Pie Chart");
		pieButton.setFocusable(false);
		visualizePanel.add(pieButton);
		
		/*
		 * Here we're going to setup everything needed for the bar chart 
		 */
		
		barChartData = new DefaultCategoryDataset();		
		barChart = ChartFactory.createBarChart("Number of Vaccines Administered per Type", "Vaccine Type", "Number of Doses Administered", barChartData, PlotOrientation.VERTICAL, false, true, false);
		barChartPlot = barChart.getCategoryPlot();
		
		barPanel = new ChartPanel(barChart);
		barPanel.setBounds(0, 0, 490, 450);
		graphPanel.add(barPanel);
		
		/*
		 * Here we're going to setup everything needed for the pie chart
		 */
		
		pieChartData = new DefaultPieDataset();
		pieChart = ChartFactory.createPieChart("Number of Vaccines Administered per Location", pieChartData, true, true, false);
		PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(  
		        "{0} : ({1})", new DecimalFormat("0"), new DecimalFormat("0%"));  
		    ((PiePlot) pieChart.getPlot()).setLabelGenerator(labelGenerator); 
		    	
		piePanel = new ChartPanel(pieChart);
		piePanel.setSize(490, 450);
		graphPanel.add(piePanel);
		
		/*
		 * Here we initialize all of the classes that allow us to determine actions that need to be taken whenever 
		 * something is interacted with
		 */
		AboutTab aboutTab = new AboutTab();
		LoadTab loadTab = new LoadTab();
		AddTab addTab = new AddTab();
		SaveTab saveTab = new SaveTab();
		VisualizeTab visualizeTab = new VisualizeTab();
		
	}
	
	/*
	 * the following method is for setting the data table. A JTable requires that you provide the column data and the rest of the data separately, hence the two arguments being passed in
	 * String[][] data is the actual data, while String[] columns is just the column data.
	 * 
	 */
	
	@SuppressWarnings("static-access")
	public void setData(String[][] data, String[] columns)
	{
		this.data = data;	//update our data
		columnNames = columns;	//update our column data
		table.setModel(new DefaultTableModel(this.data, columnNames)); //update the table to display the new data
	}
	
}

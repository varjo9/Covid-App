/*
 * This is meant to be where we create and put together all of the major elements in the GUI.
 * The frame does not have a layout, elements are placed manually
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class mainFrame 
{
	public static JFrame frame;
	public static JPanel aboutPanel;
	public static JPanel addPanel;
	public static JPanel visualizePanel;
	public static JTable table;
	public static JScrollPane scrollPane;

	static String[] columnNames = {}; //table's columns
	static String[][] data = {}; //rest of tables data
	private static JLabel idLabel;
	private static JLabel lastNameLabel;
	private static JLabel firstNameLabel;
	private static JLabel vTypeLabel;
	private static JLabel vDateLabel;
	private static JLabel vLocationLabel;
	
	public static JButton aboutButton;
	public static JButton loadButton;
	public static JButton addButton;
	public static JButton saveButton;
	public static JButton visualizeButton;
	public static JTextField idInput;
	public static JTextField lastNameInput;
	public static JTextField firstNameInput;
	public static JTextField vTypeInput;
	public static JTextField vDateInput;
	public static JTextField vLocationInput;
	
	public static JButton submitButton;
	
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
		 * Creates all of the panels (except the scrollPanel, which is added later) and adds 
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
		 * Here we create all of the textFields and add them to the addPanel
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
		table.setBorder(new LineBorder(Color.GREEN, 2));
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(data, columnNames));
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
			
		scrollPane = new JScrollPane(table);
		scrollPane.setViewportBorder(new LineBorder(Color.BLUE, 2));
		scrollPane.setBounds(200, 0, 500, 500);
		frame.getContentPane().add(scrollPane);
		
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
	 * the following methods are for getting and setting the table data. JTable requires two separate inputs,
	 * the column data and then the rest of the data, hence the two getters. These two inputs were combined in the setter
	 * for simplicity.
	 * 
	 * Getters are going to be used by the VisualizeTab to retrieve the data that has been loaded in
	 */
	
	public String[][] getData()
	{
		return data;
	}
	
	public String[] getColumnNames()
	{
		return columnNames;
	}
	
	public void setData(String[][] data, String[] columns)
	{
		this.data = data;
		columnNames = columns;
		table.setModel(new DefaultTableModel(this.data, columnNames)); //update the table to display the new data
	}
}

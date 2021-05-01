package edu.seminolestate.term;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import org.apache.ibatis.jdbc.ScriptRunner;


public class AssetDBApplication extends JFrame {
	private JTextArea textArea;
    private JButton buttonAdd = new JButton("Add Asset");
    private JButton buttonShowAll = new JButton("Show All Assets");
    private JButton buttonEdit = new JButton("Edit an Asset");
    private JButton buttonDelete = new JButton("Delete an Asset");
  
   
    
    /**
     * Define the dimensions of the GUI window
     */
    public AssetDBApplication() {
        super("Asset Viewer");
         
        textArea = new JTextArea(50, 10);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new Deida_CustomOutputStream(textArea));
        
        System.setOut(printStream);
        System.setErr(printStream);
 
        // create the GUI
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        
        add(buttonAdd, constraints);
        constraints.gridx = 1;
        add(buttonShowAll, constraints);
        constraints.gridx = 2;
        add(buttonEdit, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(buttonDelete, constraints);
       
         
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
         
        add(new JScrollPane(textArea), constraints);
        
        buttonAdd.addActionListener(new ActionListener() {
            /**
             *Listens for input to the PRINT button. Text Analyzer runs when pressed.
             *
             */
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	textArea.setText("");
                	add();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
        
        buttonShowAll.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	textArea.setText("");
                	showAll();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
        
        buttonEdit.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	textArea.setText("");
                	edit();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
        
        buttonDelete.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	textArea.setText("");
                	delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 350);
        setLocationRelativeTo(null);
    }
    
    private void add() throws IOException {
    	//Initialize variables
    	String response;
    	PreparedStatement insertAsset;
    	
    	String dateAssigned;
    	String purchaseDate;
    	String brand;
    	String model;
    	String series;
    	String serviceTag;
    	String serialNum;
    	String cost;
    	String roomNum;
    	String employeeID;
    	String assetTypeID;
    	
    	//Input dialog boxes to save responses to variables
    	response = JOptionPane.showInputDialog("Type an existing room number");
    	roomNum = response;
    	response = JOptionPane.showInputDialog("Enter assigned EmployeeID");
    	employeeID = response;
    	response = JOptionPane.showInputDialog("Enter asset type ID");
    	assetTypeID = response;
    	response = JOptionPane.showInputDialog("Type asset Date Assigned (ex. YYYY-MM-DD");
    	dateAssigned = response;
    	response = JOptionPane.showInputDialog("Type asset Purchase Date (ex. YYYY-MM-DD");
    	purchaseDate = response;
    	response = JOptionPane.showInputDialog("Type asset Brand");
    	brand = response;
    	response = JOptionPane.showInputDialog("Type asset Model");
    	model = response;
    	response = JOptionPane.showInputDialog("Type asset Series");
    	series = response;
    	response = JOptionPane.showInputDialog("Type asset Service Tag");
    	serviceTag = response;
    	response = JOptionPane.showInputDialog("Type asset Serial Number");
    	serialNum = response;
    	response = JOptionPane.showInputDialog("Type asset Cost (ex. 19.99)");
    	cost = response;
    	
    	
    	//create SQL query with response data
    	String sqlStatement = "INSERT INTO Assets (EmployeeID, AssetTypeID, RoomNum, Brand, Model, Series, ServiceTag, SerialNum,"
    						+ "DatePurchased, DateAssigned, Cost) " 
    						+ "VALUE(" + employeeID + "," + assetTypeID + "," + roomNum + "," + "\"" + brand + 
    						"\"" + "," + "\"" + model + "\"" + "," +  "\"" + series + "\"" + "," +
    						"\"" + serviceTag + "\"" + "," + serialNum + "," + "\"" + purchaseDate + "\""  + "," + 
    						"\"" + dateAssigned + "\"" + "," + cost +");"; 
    	
    	
    	 try{
    		Connection conn = getConnection(); // open DB connection
    		
    		insertAsset = conn.prepareStatement(sqlStatement); // set PreparedStatement value
    		
    		
    		insertAsset.executeUpdate(); // execute PreparedStatement
    		conn.close(); // close DB connection 
    		textArea.setText("Asset was added to the database.");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    	
    	
    
    	
    }
    
    private void showAll() throws IOException {
    	//The logic to show all assets in the database goes here
    	String displayText = "";
    	try{
    		Connection conn = getConnection(); // open db connection
    		
    		//select statement for all assets in a prepared statement
    		PreparedStatement statement = conn.prepareStatement("SELECT * FROM assets"); 
    		
    		ResultSet result = statement.executeQuery(); //execute SELECT statement
    		
    		ArrayList<String> array = new ArrayList<String>(); //array to store individual records
    		array.add(" Asset ID | RoomNum | EmployeeID | AssetTypeID | Brand | Model | Series | "
    				+ "ServiceTag | SerialNum | DatePurchased | DateAssigned | Cost \n \n");
    		while(result.next()){
    			
    			//recursively show all in textArea
    			displayText = result.getString("AssetID") + " | " + result.getString("RoomNum") + " | " +
    					result.getString("AssetTypeID") +  " | " + result.getString("EmployeeID") + " | " + 
    					result.getString("brand") +  " | " + result.getString("Model") + " | " + 
    					result.getString("Series") + " | " + result.getString("ServiceTag") + " | " + 
    					result.getString("SerialNum") + " | " + result.getString("DatePurchased") + " | " + 
    					result.getString("DateAssigned") + " | $" + result.getString("Cost") + "\n \n";
    			array.add(displayText);
    		}
    		
    		textArea.setText(array.toString());
    		conn.close(); //close db connection
    	}catch(Exception e){
    		e.printStackTrace();
    	}	
    }
    
    private void edit() throws IOException {
    	//The logic for viewing one asset in the database goes here
    	String response;
    	String assetID;
    	String employeeID;
    	String roomNum;
    	String dateAssigned;
    	Connection conn;
    	//display input dialog
    	response = JOptionPane.showInputDialog("Enter AssetID of asset to be editted");
    	assetID = response;
    	
    	//change employee/ location prompt for both? or prompt for all 
    	response = JOptionPane.showInputDialog("Enter new employeeID assignment.");
    	employeeID = response;
    	
    	response = JOptionPane.showInputDialog("Enter new room number assignment.");
    	roomNum = response;
    	
    	response = JOptionPane.showInputDialog("Enter new assignmentdate. (YYYY-MM-DD)");
    	dateAssigned = response;
    	
    	if(roomNum != null && employeeID != null){
    		try{
    			conn = getConnection();
    			
    			PreparedStatement updateEmpStatement = conn.prepareStatement("UPDATE assets " + 
    			"SET EmployeeID=" + employeeID + " WHERE AssetID=" + assetID);
    			
    			PreparedStatement updateRoomStatement = conn.prepareStatement("UPDATE assets " + 
    	    	"SET RoomNum=" + roomNum + " WHERE AssetID=" + assetID);
    			
    			PreparedStatement updateDateStatement = conn.prepareStatement("UPDATE assets " + 
    	    	    	"SET DateAssigned=\"" + dateAssigned + "\" WHERE AssetID=" + assetID);
    			
    			
    			updateEmpStatement.executeUpdate();
    			updateRoomStatement.executeUpdate();
    			updateDateStatement.executeUpdate();
    	    	conn.close();//close db connection
    	    	textArea.setText("Asset: " + assetID + "\nEmployeeID update to: " + employeeID +
    	    			"\nRoomNum update to: " + roomNum + "\nDateAssigned update to: " + dateAssigned);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    }

    private void delete()throws Exception {
		//The logic to delete an asset in the database goes here
    	String response;
    	String assetID;
    	//select by AssetID 
    	
    	response = JOptionPane.showInputDialog("Enter asset ID of asset to be deleted.");
    	assetID = response;
    	try{
    		Connection conn = getConnection(); //open DB connection
    		//prepare DELETE statement
    		PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM assets WHERE AssetID='"+ assetID + "';");
    		
    		if(assetID == null){
    			conn.close(); //close DB connection if assetID response is null
    			textArea.setText("No assetID was entered.");
    		}else{
    			deleteStatement.executeUpdate(); //execute PreparedStatement
        		textArea.setText("Asset was deleted.");
    		}
    		
    		conn.close(); //close DB connection 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
	public static Connection getConnection() throws Exception {
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/AssetDB";
			String username = "root";
			String password = "root";
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("==CONNECTION SUCCESS==");
			return con;
		}
		
		catch(Exception e) {
			System.out.println("Something went wrong.");
		}
		
		return null;
	}
    
	/**
	 * The Main method which opens the GUI in a new window.
	 * @param args s
	 * @throws IOException s
	 */
	public static void main(String[] args) throws IOException, Exception {
		//establish db connection and run SQL scripts to create schema and add data
		try{
	    	Connection conn = getConnection(); //open DB connection
	    	ScriptRunner sr = new ScriptRunner(conn); //pass connection to ScriptRunner object
	    	
	    	
	    	Reader reader = //point FileReader to CreateSchema.sql in project directory
		    		new BufferedReader(new FileReader("C:\\Users\\smith\\workspace\\"
		    		+ "CEN4333Term\\CEN4333Term\\src\\sql\\CreateSchema.sql"));
	    	  
		    sr.runScript(reader); //execute script with ScriptRunner object
		    
		    reader = //point FileReader to EmployeeMockData.sql in project directory
		    		new BufferedReader(new FileReader("C:\\Users\\smith\\workspace\\"
		    		+ "CEN4333Term\\CEN4333Term\\src\\sql\\EmployeeMockData.sql"));
		    sr.runScript(reader);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	      
	      
	      
		//TODO run SQL scripts to insert mock data into tables for user
		SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new AssetDBApplication().setVisible(true);
            }
        });
	}
}

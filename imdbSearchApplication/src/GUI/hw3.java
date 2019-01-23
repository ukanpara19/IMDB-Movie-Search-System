package GUI;

import java.sql.*;
import java.util.ArrayList;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;



import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.Window.Type;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;


public class hw3 {

	private JFrame frmMovieDatabaseSearch;
	static Connection con = null;
	//static Statement stmt = null;
	static ResultSet result = null;
	static PreparedStatement prepStatement = null;
	static PreparedStatement prepStatement2 = null;
	
	
	private JList<String> genreJList, countryJList, movieQJList, userQJList, filming_location_list ;
	
	DefaultListModel genreModel = new DefaultListModel();
	DefaultListModel countryModel = new DefaultListModel();
	DefaultListModel locationModel = new DefaultListModel();
	DefaultListModel directorModel = new DefaultListModel();
	DefaultListModel tagModel = new DefaultListModel();
	DefaultListModel movieModel = new DefaultListModel();
	DefaultListModel userModel = new DefaultListModel();
	
//	AutoCompleteDecorator decorator;
	
	ArrayList<String> genreList = new ArrayList();
	ArrayList<String> countryList = new ArrayList();
	ArrayList<String> locationList = new ArrayList();
	ArrayList<String> tagList = new ArrayList();
	ArrayList<String> tagList_1 = new ArrayList();
	ArrayList<String> movieList = new ArrayList();
	ArrayList<String> userList = new ArrayList();
	private String start = "", stop = "", movie="", movieC="",movieD="", tagM="", value = "", movieS="", user="", country = "", tags="", resultQuery = "", resultQuery2 = "", stars = "", reviews = "";
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox, comboBox_6, comboBox_1, comboBox_2;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtEnterValueBetween;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw3 window = new hw3();
					window.frmMovieDatabaseSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public hw3() {
		initialize();
		run();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void run(){
		try{
			con = openConnection();
			populateGenre();
		} catch (SQLException e){
			System.err.println("Error occured when communicating with the database server: " + e.getMessage());
		} catch (ClassNotFoundException e){
			System.err.println("Cannot find the database driver");
		} finally{
			
		}
	}
	
	private void initialize() {
		frmMovieDatabaseSearch = new JFrame();
		frmMovieDatabaseSearch.setTitle("Movie Search Database");
		frmMovieDatabaseSearch.getContentPane().setBackground(SystemColor.menu);
		frmMovieDatabaseSearch.setBounds(100, 100, 1075, 743);
		frmMovieDatabaseSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMovieDatabaseSearch.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(599, 0, 460, 38);
		frmMovieDatabaseSearch.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Movie Tag Values");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBackground(SystemColor.activeCaption);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(109, 0, 227, 33);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(0, 0, 141, 38);
		frmMovieDatabaseSearch.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Genres");
		lblNewLabel_2.setBounds(0, 0, 141, 36);
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 38, 141, 185);
		frmMovieDatabaseSearch.getContentPane().add(scrollPane);
		
		genreJList = new JList<>();
		genreJList.setBackground(SystemColor.menu);
		
		genreJList.setBounds(0, 184, 139, -184);
		JPanel genrePanel = new JPanel();
		scrollPane.setViewportView(genrePanel);
		genrePanel.setBackground(new Color(240, 240, 240));
		genrePanel.setForeground(new Color(0, 128, 128));
		//genrePanel.setLayout(null);
		genrePanel.add(genreJList);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(-17, 223, 158, 30);
		frmMovieDatabaseSearch.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Movie Year");
		lblNewLabel_3.setBounds(10, 0, 135, 27);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 251, 141, 71);
		frmMovieDatabaseSearch.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("From");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(0, 3, 56, 29);
		panel_4.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("To");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(0, 43, 56, 23);
		panel_4.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(52, 6, 79, 23);
		panel_4.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
	            start=textField.getText();
	             }
	        public void keyReleased( KeyEvent e){
	        	start = textField.getText();
	        }
	        public void keyTyped( KeyEvent e){
	        	start = textField.getText();
	        }
	           });
		
		textField_1 = new JTextField();
		textField_1.setBounds(52, 43, 79, 22);
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		textField_1.addKeyListener(new KeyListener() {
	        public void keyPressed(KeyEvent e) {
	            stop=textField_1.getText();
	             }
	        public void keyReleased( KeyEvent e){
	        	stop = textField_1.getText();
	        }
	        public void keyTyped( KeyEvent e){
	        	stop = textField_1.getText();
	        }
	           });
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(142, 0, 141, 38);
		frmMovieDatabaseSearch.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Country");
		lblNewLabel_6.setBounds(0, 0, 141, 33);
		panel_5.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_7.setBounds(0, 365, 425, 64);
		frmMovieDatabaseSearch.getContentPane().add(panel_7);
		panel_7.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBox.setBounds(60, 35, 284, 23);
		comboBox.setMaximumRowCount(3);
		comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select AND, OR between attributes", "AND", "OR" }));
		panel_7.add(comboBox);
		
		JLabel lblNewLabel_7 = new JLabel("Search Between \nAttributes' Values:");
		lblNewLabel_7.setForeground(Color.DARK_GRAY);
		lblNewLabel_7.setBounds(21, 0, 360, 35);
		panel_7.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(142, 38, 141, 284);
		frmMovieDatabaseSearch.getContentPane().add(scrollPane_1);
		
		countryJList = new JList();
		countryJList.setBackground(SystemColor.menu);
		JPanel panel_6 = new JPanel();
		scrollPane_1.setViewportView(panel_6);
		panel_6.add(countryJList);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_8.setBounds(284, 0, 141, 55);
		frmMovieDatabaseSearch.getContentPane().add(panel_8);
		
		JLabel lblCast = new JLabel("Filming Location");
		lblCast.setBounds(0, 0, 147, 32);
		panel_8.add(lblCast);
		lblCast.setHorizontalAlignment(SwingConstants.CENTER);
		lblCast.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(39, 33, 61, 16);
		panel_8.add(lblCountry);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBackground(Color.LIGHT_GRAY);
		panel_11.setBounds(426, 0, 174, 38);
		frmMovieDatabaseSearch.getContentPane().add(panel_11);
		
		JLabel lblTagIdsAnd = new JLabel("Critics Rating");
		lblTagIdsAnd.setBounds(0, 0, 151, 33);
		panel_11.add(lblTagIdsAnd);
		lblTagIdsAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblTagIdsAnd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(599, 38, 460, 263);
		frmMovieDatabaseSearch.getContentPane().add(scrollPane_3);
		
		JPanel panel_13 = new JPanel();
		scrollPane_3.setViewportView(panel_13);
		
		movieQJList = new JList();
		panel_13.add(movieQJList);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(599, 305, 208, 79);
		frmMovieDatabaseSearch.getContentPane().add(panel_14);
		panel_14.setLayout(null);
		
		JLabel lblTagWeight = new JLabel("Tag Weight");
		lblTagWeight.setBounds(0, 19, 55, 23);
		lblTagWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblTagWeight.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		panel_14.add(lblTagWeight);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblValue.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lblValue.setBounds(0, 53, 55, 23);
		panel_14.add(lblValue);
		
		comboBox_6 = new JComboBox();
		comboBox_6.setBounds(79, 18, 98, 24);
		comboBox_6.setMaximumRowCount(3);
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
		panel_14.add(comboBox_6);
		
		textField_2 = new JTextField();
		textField_2.setBounds(79, 53, 98, 23);
		panel_14.add(textField_2);
		textField_2.setColumns(10);
		textField_2.addKeyListener(new KeyListener() {
			 public void keyPressed(KeyEvent e) {
		            value=textField_2.getText();
		             }
		        public void keyReleased( KeyEvent e){
		        	value = textField_2.getText();
		        }
		        public void keyTyped( KeyEvent e){
		        	value = textField_2.getText();
		        	System.out.println(value);
		        }
	           });
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(12, 470, 431, 185);
		frmMovieDatabaseSearch.getContentPane().add(scrollPane_7);
		
		JTextArea textArea = new JTextArea();
		scrollPane_7.setViewportView(textArea);
		
		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBackground(SystemColor.activeCaptionBorder);
		panel_15.setBounds(447, 389, 602, 39);
		frmMovieDatabaseSearch.getContentPane().add(panel_15);
		
		JLabel lblUserResults = new JLabel("Results");
		lblUserResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserResults.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUserResults.setBackground(SystemColor.activeCaption);
		lblUserResults.setBounds(206, 0, 200, 39);
		panel_15.add(lblUserResults);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(447, 428, 602, 265);
		frmMovieDatabaseSearch.getContentPane().add(scrollPane_4);
		
		JPanel panel_16 = new JPanel();
		scrollPane_4.setViewportView(panel_16);
		userQJList = new JList();
		panel_16.add(userQJList);
		
		JButton btnNewButton_1 = new JButton("Execute Query");
		btnNewButton_1.setBackground(new Color(64, 224, 208));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				populateMovieSearch(evt);
				//textArea.setText(movieS);
			}
		});
		btnNewButton_1.setBounds(136, 666, 180, 38);
		frmMovieDatabaseSearch.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_9 = new JLabel("Query Display:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_9.setForeground(SystemColor.menuText);
		lblNewLabel_9.setBackground(Color.DARK_GRAY);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(10, 440, 144, 37);
		frmMovieDatabaseSearch.getContentPane().add(lblNewLabel_9);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(284, 54, 141, 268);
		frmMovieDatabaseSearch.getContentPane().add(scrollPane_5);
		
		JPanel panel = new JPanel();
		scrollPane_5.setViewportView(panel);
		
		filming_location_list = new JList();
		filming_location_list.setBackground(SystemColor.menu);
		panel.add(filming_location_list);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(426, 39, 174, 135);
		frmMovieDatabaseSearch.getContentPane().add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblRating = new JLabel("Rating :");
		lblRating.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblRating.setBounds(10, 11, 61, 16);
		panel_9.add(lblRating);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"=", "<", ">", "<=", ">="}));
		comboBox_1.setMaximumRowCount(5);
		comboBox_1.setBounds(10, 38, 130, 24);
		panel_9.add(comboBox_1);
		
		JLabel lblValue_1 = new JLabel("Stars :");
		lblValue_1.setToolTipText("");
		lblValue_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblValue_1.setBounds(10, 73, 61, 16);
		panel_9.add(lblValue_1);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("Enter value between 0-10");
		textField_3.setBounds(20, 93, 107, 26);
		panel_9.add(textField_3);
		textField_3.setColumns(10);
		textField_3.addKeyListener(new KeyListener() {
	        public void keyPressed(KeyEvent e) {
	            stars=textField_3.getText();
	             }
	        public void keyReleased( KeyEvent e){
	        	stars = textField_3.getText();
	        }
	        public void keyTyped( KeyEvent e){
	        	stars = textField_3.getText();
	        }
	           });
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBounds(426, 177, 174, 144);
		frmMovieDatabaseSearch.getContentPane().add(panel_10);
		
		JLabel lblNumberOfReviews = new JLabel("Number of Reviews :");
		lblNumberOfReviews.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNumberOfReviews.setBounds(15, 11, 143, 16);
		panel_10.add(lblNumberOfReviews);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"=", "<", ">", "<=", ">="}));
		comboBox_2.setMaximumRowCount(5);
		comboBox_2.setBounds(25, 38, 110, 24);
		panel_10.add(comboBox_2);
		
		JLabel lblValue_2 = new JLabel("Value :");
		lblValue_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblValue_2.setBounds(10, 70, 61, 16);
		panel_10.add(lblValue_2);
		
		txtEnterValueBetween = new JTextField();
		txtEnterValueBetween.setColumns(10);
		txtEnterValueBetween.setBounds(25, 97, 118, 26);
		panel_10.add(txtEnterValueBetween);
		txtEnterValueBetween.addKeyListener(new KeyListener() {
	        public void keyPressed(KeyEvent e) {
	            reviews=txtEnterValueBetween.getText();
	             }
	        public void keyReleased( KeyEvent e){
	        	reviews = txtEnterValueBetween.getText();
	        }
	        public void keyTyped( KeyEvent e){
	        	reviews = txtEnterValueBetween.getText();
	        }
	           });
		
		JButton btnGetCountry = new JButton("Get Country");
		btnGetCountry.setBounds(22, 333, 119, 23);
		frmMovieDatabaseSearch.getContentPane().add(btnGetCountry);
		btnGetCountry.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				populateCountry(evt);
				textArea.setText(resultQuery);
			}
		});
		
		JButton btnGetFilmingLocation = new JButton("Get Filming location");
		btnGetFilmingLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				populateCountry_Filming_Location(evt);
				textArea.setText(resultQuery);
			}
		});
		btnGetFilmingLocation.setBounds(152, 333, 164, 23);
		frmMovieDatabaseSearch.getContentPane().add(btnGetFilmingLocation);
		
		JButton btnShowTags = new JButton("Show Tags");
		btnShowTags.setBounds(451, 316, 119, 38);
		frmMovieDatabaseSearch.getContentPane().add(btnShowTags);
		btnShowTags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateTags(e);
				textArea.setText(resultQuery);
			}
		});
		
		JButton btnFilterButton = new JButton("Filter tags");
		btnFilterButton.setBounds(843, 316, 133, 40);
		frmMovieDatabaseSearch.getContentPane().add(btnFilterButton);
		btnFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				filterTags(evt);
				textArea.setText(resultQuery);
			}
		});
		

		

	}
	private Connection openConnection() throws SQLException, ClassNotFoundException {
	    // Load the Oracle database driver 
	    DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 

	    /* 
	    Here is the information needed when connecting to a database 
	    server. These values are now hard-coded in the program. In 
	    general, they should be stored in some configuration file and 
	    read at run time. 
	    */ 
	    String host = "localhost"; 
	    String port = "1521";
	    String dbName = "orcl"; 
	    String userName = "scott"; 
	    String password = "tiger"; 

	    // Construct the JDBC URL 
	    String dbURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName; 
	    return DriverManager.getConnection(dbURL, userName, password); 
	}
	
	 /** 
     * Close the database connection 
     * @param con 
     */ 
    private void closeConnection(Connection con) { 
        try { 
            con.close(); 
        } catch (SQLException e) { 
            System.err.println("Cannot close connection: " + e.getMessage());
        } 
    }
    
    	private void populateGenre() {
        
        String genre = "SELECT DISTINCT G.GENRE FROM MOVIE_GENRES G";
         try {
             ResultSet rS = null;
             prepStatement=con.prepareStatement(genre);
             rS = prepStatement.executeQuery(genre);
             //showResultSet(rS);
             while(rS.next())
             {
                 if(!genreModel.contains(rS.getString("genre")))
                 {
                     genreModel.addElement(rS.getString("genre"));
                 }
             }
            prepStatement.close();
            rS.close();
         } catch(Exception ex) {
        	 ex.printStackTrace();
         }
         genreJList.setModel(genreModel);
    
         MouseListener mouseListener = new MouseAdapter() 
         {
        	 public void mouseClicked(MouseEvent e) 
        	 {
        		 if (e.getClickCount() == 1)
        		 {
        			 genreList = (ArrayList<String>) genreJList.getSelectedValuesList();
        		 }
        	 }
         };
         genreJList.addMouseListener(mouseListener);
    }
    	
    	private void populateCountry(ActionEvent evt) {
    
    		country = "";
    		movie = "";
  		  // TODO Auto-generated method stub
  		  
  		if(genreList.size()!=0)
  		{
  			//String country = "";
  			String bAttr = "";
  	        
  			if(comboBox.getSelectedIndex()==1)
  			{
  				bAttr = "INTERSECT";
  			}
  			else
  			{
  				if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
  				{
  					bAttr = "UNION";
  				}
  			}
  			
  			//Genre Within attributes
  			int i=0;
  			if(start.length() == 4 && stop.length() == 4) {
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'";
  			}
  			else {
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND MG.GENRE = '"+genreList.get(i)+"'";
  			}
  			
  			
  			country += "SELECT DISTINCT C.COUNTRY FROM MOVIE_COUNTRIES C \n WHERE C.MOVIEID IN ( " + movie +" ) \n";
  			
  			System.out.println(country);
  			
  			resultQuery = "SELECT DISTINCT MR.TITLE, MGR.GENRE, MR.MYEAR, MCR.COUNTRY, MLR.LOCATION, (MR.RTALLCRITICSRATING + MR.RTTOPCRITICRATING + MR.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, (MR.RTALLCRITICSNUMREVIEWS + MR.RTTOPCRICTICNUMREVIEWS + MR.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR\nWHERE MR.MOVIEID = MCR.MOVIEID AND MR.MOVIEID = MLR.MOVIEID AND MR.MOVIEID IN ( " + movie + " ) \n";
  			//resultQuery2 = "SELECT DISTINCT AVG(MR.RTALLCRITICSRATING + MR.RTTOPCRITICRATING + MR.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, AVG(MR.RTALLCRITICSNUMREVIEWS + MR.RTTOPCRICTICNUMREVIEWS + MR.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR\nWHERE MR.MOVIEID = MCR.MOVIEID AND MR.MOVIEID = MLR.MOVIEID AND MR.MOVIEID IN ( " + movie + " )\n";
  			
  			ResultSet rS = null;
  			countryModel.clear();
  			
  			try
  			{
  				prepStatement = con.prepareStatement(country);
  				rS =prepStatement.executeQuery(country);
  	               
  	                
  				while(rS.next())
  				{
  	                    
  					if(!countryModel.contains(rS.getString("COUNTRY")))
  					{
  						countryModel.addElement(rS.getString("COUNTRY"));
  					}
  				}
  				prepStatement.close();
  				rS.close();
  	                
  			}catch(Exception ex)
  			{
  				ex.printStackTrace();
  			}
  			countryJList.setModel(countryModel);
  	               
  			MouseListener mouseListener = new MouseAdapter() 
  			{
  				public void mouseClicked(MouseEvent e) 
  				{
  					if (e.getClickCount() == 1)
  					{
  						countryList = (ArrayList<String>) countryJList.getSelectedValuesList();
  						System.out.print(countryList);
  					}	
  				}
  			};
  			countryJList.addMouseListener(mouseListener);
  		}
  	}
    
    	private void populateCountry_Filming_Location(ActionEvent evt) {
    		
    		String location = "";
    		String bAttr = "";
    		movieC = "";
    		
    		if(comboBox.getSelectedIndex()==1)
    		{
    			bAttr = "INTERSECT";
    		}
    		else
    		{
    			if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
    			{
    				bAttr = "UNION";
    			}
    		}
    		  
    		if(countryList.size() == 0)
    		{
    			int start = 0;
    			int end = countryJList.getModel().getSize()-1;
    			countryJList.setSelectionInterval(start, end);
    			countryList = (ArrayList<String>) countryJList.getSelectedValuesList();	
    			//countryJList.addMouseListener(mouseListener);
    		}
    		  
    			//Genre Within attributes
    			int i=0;
    			
    			for(i=0;i<countryList.size()-1;i++)
    			{
    			location += "SELECT DISTINCT ML.LOCATION \nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND ML.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
    			movieC += "SELECT DISTINCT ML.MOVIEID\nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND ML.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
    			}    
    			
    			location += "SELECT DISTINCT ML.LOCATION\nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND ML.MOVIEID IN("+movie+")"+"\n";
    			movieC += "SELECT DISTINCT ML.LOCATION\nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND ML.MOVIEID IN("+movie+")"+"\n";
    			
    			System.out.println(movieC);
    			//String oldResult = resultQuery;
    			resultQuery = "";
    			resultQuery2 = "";
    			for(i=0;i<countryList.size()-1;i++)
    			{
    			resultQuery += "SELECT DISTINCT MR.TITLE, MGR.GENRE, MR.MYEAR, MCR.COUNTRY, MLR.LOCATION, (MR.RTALLCRITICSRATING + MR.RTTOPCRITICRATING + MR.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, (MR.RTALLCRITICSNUMREVIEWS + MR.RTTOPCRICTICNUMREVIEWS + MR.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR\nWHERE MR.MOVIEID = MCR.MOVIEID AND MR.MOVIEID = MLR.MOVIEID AND MCR.COUNTRY='"+countryList.get(i)+"' AND MR.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
    			//movieC += "SELECT DISTINCT ML.MOVIEID\nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND ML.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
    			}    
    			
    			resultQuery += "SELECT DISTINCT MR.TITLE, MGR.GENRE, MR.MYEAR, MCR.COUNTRY, MLR.LOCATION, (MR.RTALLCRITICSRATING + MR.RTTOPCRITICRATING + MR.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, (MR.RTALLCRITICSNUMREVIEWS + MR.RTTOPCRICTICNUMREVIEWS + MR.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR\nWHERE MR.MOVIEID = MCR.MOVIEID AND MR.MOVIEID = MLR.MOVIEID AND MCR.COUNTRY='"+countryList.get(i)+"' AND MR.MOVIEID IN("+movie+")"+"\n";
    			//movieC += "SELECT DISTINCT ML.LOCATION\nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND ML.MOVIEID IN("+movie+")"+"\n";
    			/*for(i=0;i<countryList.size()-1;i++)
    			{
    			resultQuery2 += "SELECT DISTINCT AVG(MR.RTALLCRITICSRATING + MR.RTTOPCRITICRATING + MR.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, AVG(MR.RTALLCRITICSNUMREVIEWS + MR.RTTOPCRICTICNUMREVIEWS + MR.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR\nWHERE MR.MOVIEID = MCR.MOVIEID AND MR.MOVIEID = MLR.MOVIEID AND MCR.COUNTRY='"+countryList.get(i)+"' AND MR.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
    			//movieC += "SELECT DISTINCT ML.MOVIEID\nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND ML.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
    			}    */
    			
    			//resultQuery2 += "SELECT DISTINCT AVG(MR.RTALLCRITICSRATING + MR.RTTOPCRITICRATING + MR.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, AVG(MR.RTALLCRITICSNUMREVIEWS + MR.RTTOPCRICTICNUMREVIEWS + MR.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR\nWHERE MR.MOVIEID = MCR.MOVIEID AND MR.MOVIEID = MLR.MOVIEID AND MCR.COUNTRY='"+countryList.get(i)+"' AND MR.MOVIEID IN("+movie+")"+"\n";
    			//resultQuery = "SELECT DISTINCT MR.TITLE, MR.MYEAR\n FROM MOVIE MR, MOVIE_LOCATIONS MC, MOVIE_GENRES MG\n WHERE MR.MOVIEID = MC.MOVIEID AND MR.MOVIEID = MG.MOVIEID AND MC.LOCATION IN ( " + movieC + ") \n";
    			ResultSet rS = null;
    			locationModel.clear();
    	          
    			try
    			{
    				prepStatement = con.prepareStatement(location);
    				rS =prepStatement.executeQuery(location);
    	               
    	            
    				while(rS.next())
    				{
    					locationModel.addElement(rS.getString("LOCATION"));

    				}
    				prepStatement.close();
    				rS.close();
    	                
    			}catch(Exception ex)
    			{
    				ex.printStackTrace();
    			}
    			filming_location_list.setModel(locationModel);
//    			comboBox_2.setModel(actorModel2);
//    			comboBox_3.setModel(actorModel3);
//    			comboBox_4.setModel(actorModel4);
    	               
    			MouseListener mouseListener = new MouseAdapter() 
    			{
    				@SuppressWarnings("unchecked")
    				public void mouseClicked(MouseEvent e) 
    				{
    					if (e.getClickCount() == 1)
    					{
    						locationList = (ArrayList<String>) filming_location_list.getSelectedValuesList();
    					}	
    				}
    			};
    			filming_location_list.addMouseListener(mouseListener);
    		//}
    	}
    	
    	private void populateMovieSearch(ActionEvent evt) {
    		ResultSet rS = null;
    		ResultSet rS2 = null;
    		System.out.println(resultQuery);
	          
  			try
  			{
  				prepStatement = con.prepareStatement(resultQuery);
  				//prepStatement2 = con.prepareStatement(resultQuery2);
  				rS =prepStatement.executeQuery(resultQuery);
  				//rS2 = prepStatement2.executeQuery(resultQuery2);
  	               
  	            movieModel.clear();  
  	            movieModel.addElement("TITLE - Genre - YEAR - COUNTRY - FILMING LOCATION - AVG RATING - AVG NUM OF REVIEWS");
  				while(rS.next())
  				{
  					movieModel.addElement(rS.getString("TITLE") + " - "+ rS.getString("GENRE") + " - " + rS.getString("MYEAR")+ " - " + rS.getString("COUNTRY")+ " - " + rS.getString("LOCATION" ) + " - " + rS.getString("AVERAGERATING") + " - " + rS.getString("AVERAGEREVIEWS") );
  				}
  				
  			}catch(Exception ex)
  			{
  				ex.printStackTrace();
  			}
  			userQJList.setModel(movieModel);
    	}
    	
    	public void filterTags(ActionEvent evt){
    		String bAttr = "";
  	        
  			if(comboBox.getSelectedIndex()==1)
  			{
  				bAttr = "INTERSECT";
  			}
  			else
  			{
  				if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
  				{
  					bAttr = "UNION";
  				}
  			}
    		String val = "";
    		
    		if(comboBox_6.getSelectedIndex() == 0) {
    			val = "=";
    		}
    		else if(comboBox_6.getSelectedIndex() == 1) {
    			val = ">";
    		}
    		else {
    			val = "<";
    		}
    		String oldResult = "";
    		int k;
    		for(k=0;k<locationList.size()-1;k++)
	  			{
	  			oldResult += "SELECT DISTINCT MR.MOVIEID\nFROM MOVIE MR, MOVIE_LOCATIONS ML\nWHERE ML.MOVIEID = MR.MOVIEID AND ML.LOCATION = '"+ locationList.get(k)+"' AND MR.MOVIEID IN("+movieC+") \n"+bAttr+"\n";
	  			}
	  			oldResult += "SELECT DISTINCT MR.MOVIEID\nFROM MOVIE MR, MOVIE_LOCATIONS ML\nWHERE ML.MOVIEID = MR.MOVIEID AND ML.LOCATION = '"+ locationList.get(k)+"' AND MR.MOVIEID IN("+movieC+") \n";
    		
    		resultQuery = "";
    		int i;
    		if(value.length() > 0) {
    			for(i = 0; i < tagList.size() - 1; i++){
    				resultQuery += "SELECT DISTINCT MR2.TITLE, MGR.GENRE, MR2.MYEAR, MCR.COUNTRY, MLR.LOCATION, (MR2.RTALLCRITICSRATING + MR2.RTTOPCRITICRATING + MR2.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, (MR2.RTALLCRITICSNUMREVIEWS + MR2.RTTOPCRICTICNUMREVIEWS + MR2.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR2, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR, TAGS T, MOVIE_TAGS MT\nWHERE MR2.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MR2.MOVIEID = MCR.MOVIEID AND MR2.MOVIEID = MLR.MOVIEID AND T.VALUE = '" + tagList.get(i) + "' AND MT.TAGWEIGHT " + val + " '" + value + "' AND MR2.MOVIEID IN("+oldResult+") \n"+bAttr+"\n";
    			}
    			resultQuery += "SELECT DISTINCT MR2.TITLE, MGR.GENRE, MR2.MYEAR, MCR.COUNTRY, MLR.LOCATION, (MR2.RTALLCRITICSRATING + MR2.RTTOPCRITICRATING + MR2.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, (MR2.RTALLCRITICSNUMREVIEWS + MR2.RTTOPCRICTICNUMREVIEWS + MR2.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR2, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR, TAGS T, MOVIE_TAGS MT\nWHERE MR2.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MR2.MOVIEID = MCR.MOVIEID AND MR2.MOVIEID = MLR.MOVIEID AND T.VALUE = '" + tagList.get(i) + "' AND MT.TAGWEIGHT " + val + " '" + value + "' AND MR2.MOVIEID IN("+oldResult+")";
    		}
    		else {
    			for(i = 0; i < tagList.size() - 1; i++){
    				resultQuery += "SELECT DISTINCT MR2.TITLE, MGR.GENRE, MR2.MYEAR, MCR.COUNTRY, MLR.LOCATION, (MR2.RTALLCRITICSRATING + MR2.RTTOPCRITICRATING + MR2.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, (MR2.RTALLCRITICSNUMREVIEWS + MR2.RTTOPCRICTICNUMREVIEWS + MR2.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR2, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR, TAGS T, MOVIE_TAGS MT\nWHERE MR2.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MR2.MOVIEID = MCR.MOVIEID AND MR2.MOVIEID = MLR.MOVIEID AND T.VALUE = '" + tagList.get(i) + "' AND MR2.MOVIEID IN("+oldResult+") \n"+bAttr+"\n";
    			}
    			resultQuery += "SELECT DISTINCT MR2.TITLE, MGR.GENRE, MR2.MYEAR, MCR.COUNTRY, MLR.LOCATION, (MR2.RTALLCRITICSRATING + MR2.RTTOPCRITICRATING + MR2.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, (MR2.RTALLCRITICSNUMREVIEWS + MR2.RTTOPCRICTICNUMREVIEWS + MR2.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR2, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR, TAGS T, MOVIE_TAGS MT\nWHERE MR2.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MR2.MOVIEID = MCR.MOVIEID AND MR2.MOVIEID = MLR.MOVIEID AND T.VALUE = '" + tagList.get(i) + "' AND MR2.MOVIEID IN("+oldResult+")";
    		}
    	}
    	
    	private void populateTags(ActionEvent evt) {
  		  // TODO Auto-generated method stub
  		  
//  		if(actorList.size()!=0)
//  		{
    		
    		String rate = "";
    		
    		if(comboBox_1.getSelectedIndex() == 0) {
    			rate = "=";
    		}
    		else if(comboBox_1.getSelectedIndex() == 1) {
    			rate = "<";
    		}
    		else if(comboBox_1.getSelectedIndex() == 2) {
    			rate = ">";
    		}
    		else if(comboBox_1.getSelectedIndex() == 3) {
    			rate = "<=";
    		}
    		else {
    			rate = ">=";
    		}
    		
    		String rev = "";
    		if(comboBox_2.getSelectedIndex() == 0) {
    			rev = "=";
    		}
    		else if(comboBox_2.getSelectedIndex() == 1) {
    			rev = "<";
    		}
    		else if(comboBox_2.getSelectedIndex() == 2) {
    			rev = ">";
    		}
    		else if(comboBox_2.getSelectedIndex() == 3) {
    			rev = "<=";
    		}
    		else {
    			rev = ">=";
    		}
  			String bAttr = "";
  	        
  			if(comboBox.getSelectedIndex()==1)
  			{
  				bAttr = "INTERSECT";
  			}
  			else
  			{
  				if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
  				{
  					bAttr = "UNION";
  				}
  			}
  			
  			if(locationList.size() == 0)
  			{
  				int start = 0;
  				int end = filming_location_list.getModel().getSize()-1;
  				filming_location_list.setSelectionInterval(start, end);
  				locationList = (ArrayList<String>) filming_location_list.getSelectedValuesList();	
  				//countryJList.addMouseListener(mouseListener);
  			}
  			
  			String location = "";
  			movieC = "";
  			tags = "";
  			int i;
  			movie = "";
  			if(start.length() == 4 && stop.length() == 4 && stars.length() == 1 && reviews.length() > 0) {
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSNUMREVIEWS " + rev + " '" + reviews + "' AND M.RTALLCRITICSRATING " + rate + " '" + stars + "' AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSNUMREVIEWS " + rev + " '" + reviews + "' AND M.RTALLCRITICSRATING " + rate + " '" + stars + "' AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'";
  			}
  			else if(start.length() == 4 && stop.length() == 4 && stars.length() == 1) {
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSRATING " + rate + " '" + stars + "' AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSRATING " + rate + " '" + stars + "' AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'";
  			}
  			else if(start.length() == 4 && stop.length() == 4 && reviews.length() > 0) {
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSNUMREVIEWS " + rev + " '" + reviews + "' AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSNUMREVIEWS " + rev + " '" + reviews + "' AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'";
  			}
  			else if(stars.length() == 1 && reviews.length() > 0) {
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSNUMREVIEWS " + rev + " '" + reviews + "' AND M.RTALLCRITICSRATING " + rate + " '" + stars + "' AND MG.GENRE = '"+genreList.get(i)+"'"+ "\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSNUMREVIEWS " + rev + " '" + reviews + "' AND M.RTALLCRITICSRATING " + rate + " '" + stars + "' AND MG.GENRE = '"+genreList.get(i)+"'";
  			}
  		
  			else if(stars.length() == 1){
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSRATING " + rate + " '" + stars + "' AND MG.GENRE = '"+genreList.get(i)+ "'\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSRATING " + rate + " '" + stars + "' AND MG.GENRE = '"+genreList.get(i)+"'";
  			}
  			else if(reviews.length() > 0){
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSNUMREVIEWS " + rev + " '" + reviews + "' AND MG.GENRE = '"+genreList.get(i) + "'\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.RTALLCRITICSNUMREVIEWS " + rev + " '" + reviews + "' AND MG.GENRE = '"+genreList.get(i)+"'";
  			} else if(start.length() == 4 && stop.length() == 4) {
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+ stop +"' AND MG.GENRE = '"+genreList.get(i)+"'";
  			}
  			else {
  				for(i=0;i<genreList.size()-1;i++)
  	  			{
  	  				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
  	  			}
  	 			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
  	  			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIE M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND MG.GENRE = '"+genreList.get(i)+"'";
  			}
  			int j = 0;
  			for(j=0;j<countryList.size()-1;j++)
			{
			location += "SELECT DISTINCT ML.LOCATION \nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(j)+"' AND ML.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
			//director += "SELECT DISTINCT D.DIRECTORNAME\nFROM MOVIE_DIRECTORS MD, MOVIES MM\nWHERE MM.MOVIEID=D.MOVIEID AND MM.MOVIEID IN("+movie+")";
			movieC += "SELECT DISTINCT ML.MOVIEID\nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(j)+"' AND ML.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
			}    
			
			location += "SELECT DISTINCT ML.MOVIEID\nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(j)+"' AND ML.MOVIEID IN("+movie+")"+"\n";
			movieC += "SELECT DISTINCT ML.MOVIEID\nFROM MOVIE_LOCATIONS ML, MOVIE_COUNTRIES MMC\nWHERE ML.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(j)+"' AND ML.MOVIEID IN("+movie+")"+"\n";
  			
  			//Genre Within attributes
  			int k = 0;
  				for(k=0;k<locationList.size()-1;k++)
  	  			{
  	  			tags += "SELECT DISTINCT T.VALUE\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_LOCATIONS ML\nWHERE ML.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND ML.LOCATION = '"+ locationList.get(k)+"' AND MT.MOVIEID IN("+movieC+") \n"+bAttr+"\n";
  	  			}
  	  			tags += "SELECT DISTINCT T.VALUE\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_LOCATIONS ML\nWHERE ML.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND ML.LOCATION = '"+ locationList.get(k)+"' AND MT.MOVIEID IN("+movieC+") \n";
  			
  			System.out.println(tags);
  			String oldResult = resultQuery;
  			resultQuery = "";
  			for(k=0;k<locationList.size()-1;k++)
  	  			{
  	  			resultQuery += "SELECT DISTINCT MR.TITLE, MGR.GENRE, MR.MYEAR, MCR.COUNTRY, MLR.LOCATION, (MR.RTALLCRITICSRATING + MR.RTTOPCRITICRATING + MR.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, (MR.RTALLCRITICSNUMREVIEWS + MR.RTTOPCRICTICNUMREVIEWS + MR.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR\nWHERE MR.MOVIEID = MCR.MOVIEID AND MR.MOVIEID = MLR.MOVIEID AND MLR.LOCATION = '"+ locationList.get(k)+"' AND MR.MOVIEID IN("+movieC+") \n"+bAttr+"\n";
  	  			}
  	  			resultQuery += "SELECT DISTINCT MR.TITLE, MGR.GENRE, MR.MYEAR, MCR.COUNTRY, MLR.LOCATION, (MR.RTALLCRITICSRATING + MR.RTTOPCRITICRATING + MR.RTAUDIENCERATING) / 3.0 AS AVERAGERATING, (MR.RTALLCRITICSNUMREVIEWS + MR.RTTOPCRICTICNUMREVIEWS + MR.RTAUDIENCENUMRATING) / 3.0 AS AVERAGEREVIEWS\nFROM MOVIE MR, MOVIE_GENRES MGR, MOVIE_COUNTRIES MCR, MOVIE_LOCATIONS MLR\nWHERE MR.MOVIEID = MCR.MOVIEID AND MR.MOVIEID = MLR.MOVIEID AND MLR.LOCATION = '"+ locationList.get(k)+"' AND MR.MOVIEID IN("+movieC+") \n";
  			//resultQuery = "SELECT DISTINCT MR.TITLE, MR.MYEAR, MG.GENRE\n FROM MOVIE MR, MOVIE_TAGS MC, MOVIE_GENRES MG \n WHERE MR.MOVIEID = MC.MOVIEID AND MR.MOVIEID = MG.MOVIEID AND MC.TAGID IN ( SELECT TG.TAGID\nFROM TAGS TG\nWHERE TG.VALUE IN ( " + tags + ")) \n";
  			ResultSet rS = null;
  	          
  			try
  			{
  				prepStatement = con.prepareStatement(tags);
  				rS =prepStatement.executeQuery(tags);
  	               
  	            tagModel.clear();  
  				while(rS.next())
  				{
  					tagModel.addElement(rS.getString("VALUE"));
  				}
  				prepStatement.close();
  				rS.close();
  	                
  				
  				
  			}catch(Exception ex)
  			{
  				ex.printStackTrace();
  			}
  			movieQJList.setModel(tagModel);
  	               
  			MouseListener mouseListener = new MouseAdapter() 
  			{
  				public void mouseClicked(MouseEvent e) 
  				{
  					if (e.getClickCount() == 1)
  					{
  						tagList = (ArrayList<String>) movieQJList.getSelectedValuesList();
  					}	
  				}
  			};
  			movieQJList.addMouseListener(mouseListener);
  		//}
  	}
}

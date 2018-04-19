import java.sql.*;
//import java.util.Arrays;
//import java.util.Random;

//import java.util.Random;
//import java.util.LinkedList;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
@SuppressWarnings("serial")
class FedexTracking extends JFrame implements ActionListener{
	
public static void main(String args[]){ 
	// Multi thread
	try{  

			
			
		/*           Updating thread                            */
		RunnableDemo R1 = new RunnableDemo("Simulation thread");
	    R1.start();
	    /*             GUI thread */
	     AwtControlDemo GUI = new  AwtControlDemo();
	     GUI.run();
	   

	}
		catch(Exception e){
			System.out.println(e);
		}  
	}

// Display Tracking details
public static String getTrackingDetails(int TrackingID){
	String trackingDetails= "";
	String dbname = "";
	String dbPassword = "";
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/core?useSSL=false",dbname,dbPassword);  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("SELECT * FROM `fedextrackpackages` WHERE `TrackingNumber` = '" + TrackingID + "'");  
		
		
		
		while(rs.next()) { 
			int Tracking = rs.getInt(1);
			//  Source city
			String sourceCity = rs.getString(2);
			String PresentCity1 = rs.getString(4);
			String PresentCity2 = rs.getString(6);
			String PresentCity3 = rs.getString(8);

			int status = rs.getInt(11); 
			
			//Destination city
			String destCity = rs.getString(10);
			String status_final ;
			trackingDetails = "#"+Tracking+":"+sourceCity+"-->" ; 
			if (!(PresentCity1.equals(""))) {
				trackingDetails +=PresentCity1+"-->" ;
			}
			if(!(PresentCity2 == null)){
				trackingDetails +=PresentCity2+"-->" ;
			}
			if(!(PresentCity3.equals(""))){
				trackingDetails +=PresentCity3+"-->" ;
			}
			trackingDetails +=destCity+"" ;
			
			if(status == 1){
				 status_final = "(Delivered)";
				
				
			}else{
				
				status_final = "(IN TRANSIT)";
			}
			trackingDetails += status_final;
			
		}
	
			con.close();  
	}
		catch(Exception e){ 
			System.out.println(e);
	}  
	return trackingDetails;
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	System.out.println("I did this");
	}  
}  
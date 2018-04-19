import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
class RunnableDemo implements Runnable {
   private Thread t;
   private String threadName;
   
   RunnableDemo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   public void run() {
      System.out.println("Running " +  threadName );

	  String[] cities = {
			  "Northborough(MA)","Edison(NJ)","Pittsburgh(PA)","Allentown(PA)",
			  "Martinsburg(WV)",
			  "Charlotte(NC)",
			  "Atlanta(GA)",
			  "Orlando(FL)",
			  "Memphis(TN)",
			  "GroveCity(OH)",
			  "Indianapolis(IN)",
			  "Detroit(MI)",
			  "NewBerlin(WI)",
			  "Minneapolis(MN)",
			  "St.Louis(MO)",
			  "Kansas(KS)",
			  "Dallas(TX)",
			  "Houston(TX)",
			  "Denver(CO)",
			  "SaltLakeCity(UT)",
			  "Phoenix(AZ)",
			  "LosAngeles(CA)",
			  "ChinO(CA)",
			  "Sacramento(CA)",
			  "Seattle(WA)"
}; 
	 int source = 0;
	 int dest = 0;
	  
      try {
    	  	// Connect database
    	  	Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/core?useSSL=false","root","%maluvava960%");  
			Statement stmt=con.createStatement();  
			
				int i = 0;
				while(i != 6000) { // 6 seconds
					// Get all packages -- Retrieve data 
					ResultSet rs=stmt.executeQuery("select * from fedextrackpackages");  
					
					LinkedList<Vertex> path =  new LinkedList<Vertex>();
					while(rs.next()){
						// Tracking number
						int Tracking = rs.getInt(1);
						//  Source city
						String sourceCity = rs.getString(2);
						source = Arrays.asList(cities).indexOf(sourceCity);
						//Destination city
						String destCity = rs.getString(10);
						dest = Arrays.asList(cities).indexOf(destCity);
						/*  Present cities: 
						
					
 */
						 String PresentCity1 = rs.getString(4);
						  String PresentCity2 = rs.getString(6);
						  String PresentCity3 = rs.getString(8);
						//	String PresentCityTS2 = rs.getString(7);
						
						 // String PresentCityTS3 = rs.getString(8);
                        //	String PresentCityTS3 = rs.getString(9);
                                             
						System.out.println(Tracking +" "+ sourceCity +" - "+source +" "+destCity + " "+dest);
						
						
						
						try{
							path = TestDijAlgoritm.testExcute(source,dest);
							
							 for (Vertex vertex : path) {
								String newCity = vertex.getName();
								 if(newCity.equals(destCity)){
									 updateStatus(Tracking);
									 System.out.println("Status: Delivered");
									 
								 }
								 if (PresentCity1.equals("") && newCity !=sourceCity) {
									 PresentCity1 = newCity;
									 updateCity(Tracking,"PresentCity",newCity,"0");
									
									
								 }
								 else if(PresentCity2 == null){
									 PresentCity2 = newCity;
										 updateCity(Tracking,"PresentCity2",newCity,"0");
										
									 
									 
								 }
								 else if(PresentCity3.equals("") || !(PresentCity3.equals(destCity)) ){
									 PresentCity3 = newCity;
									updateCity(Tracking,"PresentCity3",newCity,"0");
									
								 
								 
							 }
							
									System.out.println("Moving package #"+Tracking);
								//TODO UPDATE
							 }    
							
						}
						catch (Exception e){
						}						
						/*
						source = Arrays.asList(cities).indexOf("SaltLakeCity(UT)");
						System.out.println(source);
						
						 * 
						 */
					}
				// Retrieve data, And update next city for the loop
	    		 System.out.println("Thread: " + threadName + ", " + i);
	            // Let the thread sleep for a while.
	            Thread.sleep(50);
	            i++;
			}
      }catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      System.out.println("Thread " +  threadName + " exiting.");
   }
   

private void updateStatus(int Tracking) throws SQLException {
	Connection con3=DriverManager.getConnection("jdbc:mysql://localhost:3307/core?useSSL=false","root","%maluvava960%");  
	Statement stmt3=con3.createStatement();
	String statement3 = "UPDATE `fedextrackpackages` SET `Status` = '1' WHERE `TrackingNumber` = '"+Tracking+"' ";
	System.out.println(statement3);
	stmt3.executeUpdate(statement3); 
	
	con3.close();
}

public void start (){
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();

      }
   }
// Method to update the city in database
public void updateCity(int Tracking, String fieldname,String value, String status) throws SQLException{
	Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3307/core?useSSL=false","root","%maluvava960%");  
	Statement stmt2=con2.createStatement();
	String statement = "UPDATE `fedextrackpackages` SET `"+fieldname+"` = '"+value+"' WHERE `TrackingNumber` = "+Tracking+" ";
	System.out.println(statement);
	stmt2.executeUpdate(statement); 
	
	con2.close();
		
}





}

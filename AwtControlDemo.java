//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AwtControlDemo implements Runnable{
	   private Frame mainFrame;
	   private Label headerLabel;
	   private Label statusLabel;
	   private Panel controlPanel;
	   static int Trackingnumber;

	   public AwtControlDemo(){
	      prepareGUI();
	   }

	   private void prepareGUI(){
		      mainFrame = new Frame("Fedex Tracking System");
		      mainFrame.setSize(400,400);
		      mainFrame.setLayout(new GridLayout(3, 1));
		      mainFrame.addWindowListener(new WindowAdapter() {
		         public void windowClosing(WindowEvent windowEvent){
		            System.exit(0);
		         }        
		      });    
		      headerLabel = new Label();
		      headerLabel.setAlignment(Label.CENTER);
		      statusLabel = new Label();        
		      statusLabel.setAlignment(Label.CENTER);
		      statusLabel.setSize(350,100);

		      controlPanel = new Panel();
		      controlPanel.setLayout(new FlowLayout());

		      mainFrame.add(headerLabel);
		      mainFrame.add(controlPanel);
		      mainFrame.add(statusLabel);
		      mainFrame.setVisible(true);  
		   }

		   void showTextFieldDemo(){
		      headerLabel.setText("Welcome to Fedex Tracking Application!"); 
		   
		      Label  namelabel= new Label("Enter your Tracking number: ", Label.RIGHT);
		      
		      final TextField userText = new TextField(6);
		 //     passwordText.setEchoChar('*');

		      Button loginButton = new Button("Find your package");
		   
		      loginButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {     
		            String data = "Tracking details:";
		            Trackingnumber = Integer.parseInt(userText.getText());
		            data += FedexTracking.getTrackingDetails(Trackingnumber);
		            
		            
		            statusLabel.setText(data);        
		         }
		      }); 

		      controlPanel.add(namelabel);
		      controlPanel.add(userText);
		       
		    //  controlPanel.add(passwordText);
		      controlPanel.add(loginButton);
		      mainFrame.setVisible(true);  
		   }

		@Override
		public void run() {
			// TODO Auto-generated method stub
			AwtControlDemo  awtControlDemo = new AwtControlDemo();
			awtControlDemo.showTextFieldDemo();
		}

		public void start() {
			// TODO Auto-generated method stub
			
		}
		}

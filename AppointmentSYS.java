//Sean Healy T00141773
/*All code for the saving and displaying from file were got from the sample 
 *programs and refactored*/
 //Alot of help from John Walsh and from sites like stackoverflow.com and the java api

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
 
public class AppointmentSYS extends JFrame implements ActionListener {

	private JMenuBar jBar;
	private Container cPane;
	private JTextArea textArea;
	private JLabel backGround;
	private JButton shopBtn;
	private JButton martinBtn;
	private JButton marekBtn;
	private JButton guestBtn;
	Appointment appointment;
	OpeningHours hours;
	static LinkedList <Appointment> martyApps;
	static LinkedList <Appointment> marekApps;
	static LinkedList <Appointment> guestApps;
	
	
	public static void main(String [] args) //Main Method
	{
		AppointmentSYS frame = new AppointmentSYS();
		frame.setVisible(true);
		//frame.revalidate();
			
		martyApps = new LinkedList<Appointment>();
		marekApps = new LinkedList<Appointment>();
		guestApps = new LinkedList<Appointment>();
		
	
		
	}//End of Main
	
	public AppointmentSYS(){//JFrame constructor
		
		setTitle("Appointment System");
		setSize(960,918);
		setLocation(450,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		cPane = getContentPane();
		cPane.setLayout(new FlowLayout());
		
		
		
		//Setting menu bar
		jBar = new JMenuBar();
		
		setJMenuBar(jBar);
		createScheduleMenu(); 
		createAppointmentsMenu();
		createHoursMenu();
		createButtonMenu();
		textArea = new JTextArea();
		backGround = new JLabel();
		
	}//end of JFrame constructor
	
	public void createButtonMenu(){//create button method
		shopBtn = new JButton("Shop");
		cPane.add(shopBtn);
		shopBtn.addActionListener(this);
		
		martinBtn = new JButton("Martin Tattoo");
		cPane.add(martinBtn);
		martinBtn.addActionListener(this);
		
		marekBtn = new JButton("Marek Tattoo");
		cPane.add(marekBtn);
		marekBtn.addActionListener(this);
		
		guestBtn = new JButton("Guest Tattoo");
		cPane.add(guestBtn);
		guestBtn.addActionListener(this);		
	}//end of create button method
	
	public void createScheduleMenu(){// Schedule method
		
		JMenu scheduleMenu = new JMenu("Schedule");
		jBar.add(scheduleMenu);
		
		JMenuItem martinItem = new JMenuItem("Martin");
		scheduleMenu.add(martinItem);
		martinItem.addActionListener(this);
		
		JMenuItem marekItem = new JMenuItem("Marek");
		scheduleMenu.add(marekItem);
		marekItem.addActionListener(this);
		
		JMenuItem guestItem = new JMenuItem("Guest");
		scheduleMenu.add(guestItem);
		guestItem.addActionListener(this);
		
		scheduleMenu.addSeparator();
		
		JMenuItem quitItem = new JMenuItem("Quit");
		scheduleMenu.add(quitItem);
		quitItem.addActionListener(this);
	}//end of schedule method
	
	public void createAppointmentsMenu(){//Appointment method
		
		JMenu appointmentsMenu = new JMenu("Appointments");
		jBar.add(appointmentsMenu);
		
		JMenuItem addItem = new JMenuItem("Add");
		appointmentsMenu.add(addItem);
		addItem.addActionListener(this);
	}//End of appointment method
	
	public void createHoursMenu(){//Appointment method
		
		JMenu openingHoursMenu = new JMenu("Opening Hours");
		jBar.add(openingHoursMenu);
		
		JMenuItem hoursItem = new JMenuItem("Hours");
		openingHoursMenu.add(hoursItem);
		hoursItem.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){//ActionPerformed method
		
		if(e.getActionCommand().equals("Martin"))
		{
			try{
			displayMartinSchedule();
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Fail");
			}
		}
		else if(e.getActionCommand().equals("Marek"))
		{
			try{
			displayMarekSchedule();
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Fail");
			}
		}
		else if(e.getActionCommand().equals("Guest"))
		{
			try{
			displayGuestSchedule();
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Fail");
			}
		}
		else if(e.getActionCommand().equals("Quit"))		
			System.exit(0);
		
		else if(e.getActionCommand().equals("Add"))
			inputInfo ();
		
		else if(e.getActionCommand().equals("Hours"))
		{
			hours = new OpeningHours();
			JOptionPane.showMessageDialog(null,hours.hoursToString());
		}
			
		else if(e.getSource() == shopBtn)
			shopImageSelect();
			
		
		else if(e.getSource() == martinBtn)
			martinImageSelect();
			
		
		else if(e.getSource() == marekBtn)	
			marekImageSelect();
			
		
		else if(e.getSource() == guestBtn)
			guestImageSelect();
			
					
	} //end of action event method
	
	public void inputInfo (){//Appointment info method
		
		appointment = new Appointment();
		appointment.setDate(JOptionPane.showInputDialog("Please enter appointment date (DD/MM/YY)"));
		appointment.setTime(Float.parseFloat(JOptionPane.showInputDialog("Please enter appointment time(24hr clock)")));
		appointment.setName(JOptionPane.showInputDialog("Please enter appointment name"));
		appointment.setArtist(JOptionPane.showInputDialog("Please enter artist name").toLowerCase());
		appointment.setDeposit(Integer.parseInt(JOptionPane.showInputDialog("Please enter deposit amount")));
		appointment.setPrice(Integer.parseInt(JOptionPane.showInputDialog("Please enter full price")));
		
		if(appointment.getArtist().equals("martin"))
			{
				
				try{
				martyApps.add(appointment);
				martinSchedule();
				}
				catch(Exception e){
				
				JOptionPane.showMessageDialog(null,"Appointment not stored.");
				}
			}	
			else if(appointment.getArtist().equals("marek"))
			{
				try{
				marekApps.add(appointment);
				marekSchedule();
				}
				catch(Exception e){
				
				JOptionPane.showMessageDialog(null,"Appointment not stored.");
				}
			}
				
			else if(appointment.getArtist().equals("guest"))
			{
				try{
				guestApps.add(appointment);
				guestSchedule();
				}
				catch(Exception e){
				
				JOptionPane.showMessageDialog(null,"Appointment not stored.");
				}
			}
		
	}//End of appointment info method
	
	
	public void martinSchedule() throws IOException{//Saving martins appointments
		
		File martinFile = new File("MartinFile.dat");
		FileOutputStream fos = new FileOutputStream(martinFile);
		
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(martyApps);
		os.close();
		
		JOptionPane.showMessageDialog(null,"Appointment added to Martins schedule.");
	}//end of saving method
	
	public void marekSchedule() throws IOException{//Saving mareks appointments
		
		File marekFile = new File("MarekFile.dat");
		FileOutputStream fos = new FileOutputStream(marekFile);
		
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(marekApps);
		os.close();
		JOptionPane.showMessageDialog(null,"Appointment added to Marek schedule.");
	}//end of saving method
	
	public void guestSchedule() throws IOException{//Saving guests appointments
		
		File guestFile = new File("GuestFile.dat");
		FileOutputStream fos = new FileOutputStream(guestFile);
		
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(guestApps);
		os.close();
		
		JOptionPane.showMessageDialog(null,"Appointment added to Guest schedule.");
	}//end of saving method
	
	public void displayMartinSchedule() throws Exception{//Displaying Martins appointments
		
		File martinFile = new File("MartinFile.dat");
		FileInputStream fis = new FileInputStream(martinFile);
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		LinkedList <Appointment> display = (LinkedList <Appointment> ) ois.readObject();
		
		ois.close();
		String output = "";
		for(Appointment app: display)
			output+= app.toString();
		JOptionPane.showMessageDialog(null,output);
	}//end of display method
	
	public void displayMarekSchedule() throws Exception{//Displaying Mareks appointments
		
		File marekFile = new File("MarekFile.dat");
		FileInputStream fis = new FileInputStream(marekFile);
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		LinkedList <Appointment> display = (LinkedList <Appointment> ) ois.readObject();
		
		ois.close();
		String output = "";
		for(Appointment app: display)
			output+= app.toString();
		JOptionPane.showMessageDialog(null,output);
	}//end of display method
	
	public void displayGuestSchedule() throws Exception{//Displaying Guests appointments
		
		File guestFile = new File("GuestFile.dat");
		FileInputStream fis = new FileInputStream(guestFile);
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		LinkedList <Appointment> display = (LinkedList <Appointment> ) ois.readObject();
		
		ois.close();
		String output = "";
		for(Appointment app: display)
			output+= app.toString();
		JOptionPane.showMessageDialog(null,output);
	}//end of display method
	
	public void shopImageSelect(){//Change the background image to the shop image
		
		backGround.setIcon(new ImageIcon("MetalUrges.jpg")) ;
		textArea.setText(null);
		
		cPane.add(backGround);
		backGround.revalidate();
		
		cPane.add(textArea);
		textArea.revalidate();
		
	}//end of image select
	
	public void martinImageSelect(){//change the background image to a image of martins tattoo
	
		backGround.setIcon(new ImageIcon("MartinTattoo.jpg"));
		
		textArea.setText("Martin Joy " +
							"\nMetal Urges" +
							"\n126 Lower Rock Street" +
								"\nTralee" +
									"\nCo. Kerry" +
										"\n\n (066) 7144691");	
		
		cPane.add(backGround);
		backGround.revalidate();
		
		cPane.add(textArea);
		textArea.revalidate();
		
	}//end of image select
	
	public void marekImageSelect(){//change the background image to a image of mareks tattoo

		backGround.setIcon(new ImageIcon("MarekTattoo.jpg")) ;
		
		textArea.setText("Marek Wieczorek" +
							"\nMetal Urges" +
							"\n126 Lower Rock Street" +
								"\nTralee" +
									"\nCo. Kerry" +
										"\n\n (066) 7144691");
		
		cPane.add(backGround);
		backGround.revalidate();
		
		cPane.add(textArea);
		textArea.revalidate();
		
	}//end of image select
	
	public void guestImageSelect(){//change the background image to a image of guests tattoo
		
		backGround.setIcon(new ImageIcon("ArnasTattoo.jpg")) ;
		
		textArea.setText("Arnas Blinstrubas " +
							"\nMetal Urges" +
							"\n126 Lower Rock Street" +
								"\nTralee" +
									"\nCo. Kerry" +
										"\n\n (066) 7144691");
		
		cPane.add(backGround);
		backGround.revalidate();
		
		cPane.add(textArea);
		textArea.revalidate();
				
	}//end of image select
}//end of class
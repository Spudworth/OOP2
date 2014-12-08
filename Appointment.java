import java.io.*;

public class Appointment implements Serializable{
	
	private String name, artist, date;
	private float time;
	private int deposit, price;
	
	public Appointment(){//No arg Constructor
		this("No Date", 0.0f,"No Name", "No Artist", 0, 0);
	}//End
	
	public Appointment(String date, float time, String name, String artist, int deposit, int price){//Multi arg Constructor
		
		setDate(date);
		setTime(time); 
		setName(name);
		setArtist(artist);
		setDeposit(deposit);
		setPrice(price);	
	}//End
	
	public void setDate(String date){//Mutators
		this.date = date;
	}
	public void setTime(float time){
		this.time = time;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setArtist(String artist){
		this.artist = artist;
	}
	public void setDeposit(int deposit){
		this.deposit = deposit;
	}
	public void setPrice(int price){
		this.price = price;
	}//End
	
	public String getDate(){//Accessors
		return date;
	}
	public float getTime(){
		return time;
	}
	public String getName(){
		return name;
	}
	public String getArtist(){
		return artist;
	}
	public int getDeposit(){
		return deposit;
	}
	public int getPrice(){
		return deposit;
	}//End
	
	public String toString(){
		
		String message = String.format("Date: %s \nTime: %.2f \nName: %s \nArtist: %s \nDeposit : €%d \nPrice: €%d\n\n"
									,date, time, name, artist, deposit, price);
	return message;
	}
}
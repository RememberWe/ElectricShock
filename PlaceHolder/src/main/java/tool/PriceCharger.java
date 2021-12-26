package tool;

import java.sql.Date;

public class PriceCharger {
	private String roomPrice;
	private String addPrice;
	private Date checkIn;
	private Date checkOut;
	
	public PriceCharger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PriceCharger(String roomPrice, String addPrice, Date checkIn, Date checkOut) {
		super();
		this.roomPrice = roomPrice;
		this.addPrice = addPrice;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public String getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(String roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(String addPrice) {
		this.addPrice = addPrice;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	
	public static String charger(String room, int quantity, String add, Date in, Date out) {
		
		int roomPrice = Integer.parseInt(room);
		
		int addPrice;
		if(Integer.parseInt(add) > 2) {
			addPrice = (Integer.parseInt(add) - 2) * 30000;
		} else {
			addPrice = 0;
		}
		
		long difference = (out.getTime() -  in.getTime()) / (1000 * 24 * 60 * 60); 
		
		int nights = (int) difference;
		System.out.println(nights);
		
		int totalCharge = (roomPrice + addPrice) * nights * quantity;
		String money = String.format("%,d", totalCharge);
		return money;
	}
}

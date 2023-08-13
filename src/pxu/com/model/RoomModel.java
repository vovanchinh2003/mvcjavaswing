package pxu.com.model;

public class RoomModel {
	private String room_id,room_type;
	private int bed_count,occupancy_count;
	private float room_price;
	
	
	
	public RoomModel() {
		super();
	}
	public RoomModel(String room_id, String room_type, int bed_count, int occupancy_count, float room_price) {
		super();
		this.room_id = room_id;
		this.room_type = room_type;
		this.bed_count = bed_count;
		this.occupancy_count = occupancy_count;
		this.room_price = room_price;
	}
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public int getBed_count() {
		return bed_count;
	}
	public void setBed_count(int bed_count) {
		this.bed_count = bed_count;
	}
	public int getOccupancy_count() {
		return occupancy_count;
	}
	public void setOccupancy_count(int occupancy_count) {
		this.occupancy_count = occupancy_count;
	}
	public float getRoom_price() {
		return room_price;
	}
	public void setRoom_price(float room_price) {
		this.room_price = room_price;
	}
	
	
}

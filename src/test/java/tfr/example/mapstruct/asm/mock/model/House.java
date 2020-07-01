package tfr.example.mapstruct.asm.mock.model;

import java.io.Serializable;
import java.util.List;

public class House  {
	private String serial;

	private String name;

	private List<Floor> floors;

	private List<Room> rentRooms;

	private HousekeeperCompany housekeeperCompany;

	public List<Floor> getFloors() {
		return floors;
	}

	public HousekeeperCompany getHousekeeperCompany() {
		return housekeeperCompany;
	}

	public Serializable getUniqueId() {
		return serial;
	}

	public String getName() {
		return name;
	}

	public List<Room> getRentRooms() {
		return rentRooms;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	public void setHousekeeperCompany(HousekeeperCompany housekeeperCompany) {
		this.housekeeperCompany = housekeeperCompany;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRentRooms(List<Room> rentRooms) {
		this.rentRooms = rentRooms;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Override
	public String toString() {
		return "House{" + "serial='" + serial + '\'' + '}';
	}
}



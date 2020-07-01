package tfr.example.mapstruct.asm.mock.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HousekeeperCompany  {

	private String serial;
	private String name;

	private Housekeeper housekeeper;

	private List<House> houses = new ArrayList<>();

	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}

	public Housekeeper getHousekeeper() {
		return housekeeper;
	}

	public String getName() {
		return name;
	}

	public String getSerial() {
		return serial;
	}

	public Serializable getUniqueId() {
		return serial;
	}

	public void setHousekeeper(Housekeeper housekeeper) {
		this.housekeeper = housekeeper;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
}

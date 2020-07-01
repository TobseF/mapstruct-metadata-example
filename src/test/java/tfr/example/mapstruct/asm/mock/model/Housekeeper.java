package tfr.example.mapstruct.asm.mock.model;

import java.io.Serializable;

public class Housekeeper  {

	private String serial;
	private String name;
	private HousekeeperCompany housekeepingCompany;

	public HousekeeperCompany getHousekeepingCompany() {
		return housekeepingCompany;
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

	public void setHousekeepingCompany(HousekeeperCompany housekeepingCompany) {
		this.housekeepingCompany = housekeepingCompany;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

}

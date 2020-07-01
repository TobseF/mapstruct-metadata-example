package tfr.example.mapstruct.asm.mock.uimodel;


import java.util.Objects;

public class HouseUiModel extends PersistedUiModel {

   private String name  = null;
   private String keeperName = null;
   private String roomNumber= null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeeperName() {
		return keeperName;
	}

	public void setKeeperName(String keeperName) {
		this.keeperName = keeperName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (!(o instanceof HouseUiModel)) { return false; }
		HouseUiModel that = (HouseUiModel) o;
		return Objects.equals(name, that.name) && Objects.equals(keeperName, that.keeperName) && Objects.equals(roomNumber, that.roomNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, keeperName, roomNumber);
	}

	@Override
	public String toString() {
		return "HouseUiModel{" + "name='" + name + '\'' + ", keeperName='" + keeperName + '\'' + ", roomNumber='" + roomNumber + '\'' + '}';
	}
}

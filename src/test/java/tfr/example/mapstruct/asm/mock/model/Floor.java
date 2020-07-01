package tfr.example.mapstruct.asm.mock.model;


import java.io.Serializable;
import java.util.List;

public class Floor  {
    private String serial;
    private String name;
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public Serializable getUniqueId() {
        return serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Floor{" + "serial='" + serial + '\'' + '}';
    }
}

package tfr.example.mapstruct.asm.mock.model;

import java.io.Serializable;

public class Room   {
    private String serial;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Serializable getUniqueId() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Room{" + "serial='" + serial + '\'' + '}';
    }
}

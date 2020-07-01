package tfr.example.mapstruct.asm.util;


import java.io.Serializable;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Mapper which handles the transformation between {@link Serializable} and {@link String}.
 * Useful for mapping a unique id / serial / foreign key).
 */
public class SerialMapper {

    public String serializableToString(Serializable serializable) {
        return ofNullable(serializable).map(Objects::toString).orElse("");
    }

    public String stringToString(String string) {
        return string;
    }

    public Serializable stringToSerializable(String string) {
        return string;
    }
}

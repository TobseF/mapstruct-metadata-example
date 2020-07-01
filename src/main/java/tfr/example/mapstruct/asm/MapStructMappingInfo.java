package tfr.example.mapstruct.asm;

import java.util.Objects;

public class MapStructMappingInfo {

	private final String sourceName;
	private final String targetName;

	public MapStructMappingInfo(String sourceName, String targetName) {
		this.sourceName = sourceName;
		this.targetName = targetName;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) { return true; }
		if (!(o instanceof MapStructMappingInfo)) { return false; }
		MapStructMappingInfo that = (MapStructMappingInfo) o;
		return Objects.equals(sourceName, that.sourceName) && Objects.equals(targetName, that.targetName);
	}

	public String getSourceName() {
		return sourceName;
	}

	public String getTargetName() {
		return targetName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sourceName, targetName);
	}

	@Override
	public String toString() {
		return "MapStructMappinginfo{" + "sourceName='" + sourceName + '\'' + ", targetName='" + targetName + '\'' + '}';
	}
}

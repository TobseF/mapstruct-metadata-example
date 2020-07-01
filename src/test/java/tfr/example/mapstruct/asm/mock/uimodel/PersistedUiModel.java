package tfr.example.mapstruct.asm.mock.uimodel;

import java.util.Objects;

public class PersistedUiModel {
	private String uniqueId;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (!(o instanceof PersistedUiModel)) { return false; }
		PersistedUiModel that = (PersistedUiModel) o;
		return Objects.equals(uniqueId, that.uniqueId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uniqueId);
	}
}

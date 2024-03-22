package scrips.datamigration.sss.model;

import java.io.Serializable;
import java.util.Objects;

public class JpaSssFloatingRatesId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String referenceRate;
	private Integer valueDate;
	@Override
	public int hashCode() {
		return Objects.hash(referenceRate, valueDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JpaSssFloatingRatesId other = (JpaSssFloatingRatesId) obj;
		return Objects.equals(referenceRate, other.referenceRate) && Objects.equals(valueDate, other.valueDate);
	}
	
}

package scrips.datamigration.rtgs.model;

import java.io.Serializable;
import java.util.Objects;

public class JpaCbmSoraRateId implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	@Column(name="reference_rate")
	private String referenceRate;
//	@Column(name="value_date")
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
		JpaCbmSoraRateId other = (JpaCbmSoraRateId) obj;
		return Objects.equals(referenceRate, other.referenceRate) && Objects.equals(valueDate, other.valueDate);
	}
	
}

package com.dataextract.sgs.model;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SssAllotmentMappingId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "ALD_ISIN")
	private String aldIsin;
	@Column(name = "ALD_ALMT_DATE")
	private String aldAlmtDate;
	@Column(name = "ALD_ISIN_TYPE")
	private String aldIsinType;
}

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
public class SssAllotmentId  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "ALM_ISIN")
	private String almIsin;
	@Column(name = "ALM_ALMT_DATE")
	private String almAlmtDate;
	@Column(name = "ALM_ISIN_TYPE")
	private String almIsinType;
}
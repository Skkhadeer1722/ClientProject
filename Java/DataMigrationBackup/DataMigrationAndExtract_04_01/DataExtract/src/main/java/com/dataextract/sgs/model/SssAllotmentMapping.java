package com.dataextract.sgs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "almt_det")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(SssAllotmentMappingId.class)
public class SssAllotmentMapping {
	@Id
	@Column(name = "ALD_ISIN")
	private String aldIsin;
	@Id
	@Column(name = "ALD_ALMT_DATE")
	private String aldAlmtDate;
	@Id
	@Column(name = "ALD_ISIN_TYPE")
	private String aldIsinType;
	@Column(name = "ALD_SETT_AMT")
	private Integer aldSettAmt;
	@Column(name = "ALD_ALMT_PRICE")
	private Double aldAlmtPrice;
	@Column(name = "ALD_NOM_AMT")
	private Integer aldNomAmt;

}
